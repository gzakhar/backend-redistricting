package edu.stonybrook.redistricting.lemonkeredistricting.service;

import edu.stonybrook.redistricting.lemonkeredistricting.models.District;
import edu.stonybrook.redistricting.lemonkeredistricting.models.Districting;
import edu.stonybrook.redistricting.lemonkeredistricting.models.PopulationType;
import edu.stonybrook.redistricting.lemonkeredistricting.models.Precinct;
import edu.stonybrook.redistricting.lemonkeredistricting.repo.DistrictingRepository;
import org.jgrapht.alg.matching.MaximumWeightBipartiteMatching;
import org.jgrapht.graph.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GillConstruct {

    public static List<District> reorderDistrictsByReference(Districting workingDistricting, Districting referenceDistricting) {
        Set<District> current = new HashSet<>(workingDistricting.getDistricts());
        Set<District> enactedDistricts = new HashSet<>(referenceDistricting.getDistricts());

        WeightedMultigraph<District, DefaultWeightedEdge> graph = new WeightedMultigraph<>(DefaultWeightedEdge.class);

        for (District enactedDistrict : enactedDistricts) {
            // add to the graph
            graph.addVertex(enactedDistrict);
        }
        for (District currentDistrict : current) {
            // add to the graph
            graph.addVertex(currentDistrict);
        }
        for (District enactedDistrict : enactedDistricts) {
            // add to the graph
            for (District currentDistrict : current) {
                //populate the graph with the edges and the corresponding weight
                DefaultWeightedEdge edge = graph.addEdge(enactedDistrict, currentDistrict);
                graph.setEdgeWeight(edge, gillObjectiveFunction(enactedDistrict, currentDistrict));
            }
        }

        MaximumWeightBipartiteMatching<District, DefaultWeightedEdge> matchings = new MaximumWeightBipartiteMatching<>(
                graph,
                enactedDistricts,
                current);

        Set<DefaultWeightedEdge> matchingEdges = matchings.getMatching().getEdges();
        //each edge is connecting enacted district to corresponding current district
        //number accordingly via population order from enacted
        List<District> enactedPopulationOrdering = referenceDistricting.getDistrictOrderPopulation();

        District[] orderedDistricting = new District[workingDistricting.getNumberDistricts()];
        for (DefaultWeightedEdge matchingEdge : matchingEdges) {
            District enactedDistrict = graph.getEdgeSource(matchingEdge);
            District currentDistrict = graph.getEdgeTarget(matchingEdge);

            orderedDistricting[enactedPopulationOrdering.indexOf(enactedDistrict)] = currentDistrict;

        }


        return Arrays.asList(orderedDistricting);
    }

    public static double populationDeviationFromReference(Districting workingDistricting, Districting referenceDistricting) {

        return populationDifference(
                workingDistricting.getTotalPopulation(PopulationType.TOTAL_POPULATION),
                workingDistricting.orderDistrictsByReference(referenceDistricting),
                referenceDistricting.getDistrictOrderPopulation());
    }

    public static double areaDeviationFromReference(Districting workingDistricting, Districting referenceDistricting) {

        return areaDifference(
                workingDistricting.getNumberPrecincts(),
                workingDistricting.orderDistrictsByReference(referenceDistricting),
                referenceDistricting.getDistrictOrderPopulation());
    }

    public static Map<Long, Long> reorderDistrictsByReferenceMap(Districting workingDistricting, Districting referenceDistricting) {

        List<District> working = (List<District>) workingDistricting.getDistricts();
        List<District> reference = (List<District>) referenceDistricting.getDistricts();

        WeightedMultigraph<District, DefaultWeightedEdge> graph = new WeightedMultigraph<>(DefaultWeightedEdge.class);

        for (District enactedDistrict : reference) {
            // add to the graph
            graph.addVertex(enactedDistrict);
        }
        for (District currentDistrict : working) {
            // add to the graph
            graph.addVertex(currentDistrict);
        }
        for (District enactedDistrict : reference) {
            // add to the graph
            for (District currentDistrict : working) {
                //populate the graph with the edges and the corresponding weight
                DefaultWeightedEdge edge = graph.addEdge(enactedDistrict, currentDistrict);
                graph.setEdgeWeight(edge, gillObjectiveFunction(enactedDistrict, currentDistrict));
            }
        }


        MaximumWeightBipartiteMatching<District, DefaultWeightedEdge> matchings =
                new MaximumWeightBipartiteMatching<>(graph, new HashSet<>(reference), new HashSet<>(working));

        Set<DefaultWeightedEdge> matchingEdges = matchings.getMatching().getEdges();

        Map<Long, Long> res = new HashMap<>();
        for (DefaultWeightedEdge matchingEdge : matchingEdges) {
            District enactedDistrict = graph.getEdgeSource(matchingEdge);
            District currentDistrict = graph.getEdgeTarget(matchingEdge);

            res.put(enactedDistrict.getDistrictId(), currentDistrict.getDistrictId());
        }

        return res;
    }

    private static double populationDifference(double denominator, List<District> d1, List<District> d2) {

        int numorator = 0;
        for (int i = 0; i < Integer.min(d1.size(), d2.size()); i++) {
            numorator += Math.abs(
                    d1.get(i).getTotalPopulation(PopulationType.TOTAL_POPULATION)
                            - d2.get(i).getTotalPopulation(PopulationType.TOTAL_POPULATION));
        }
        return numorator / denominator;
    }

    private static double areaDifference(double denominator, List<District> d1, List<District> d2) {

        int numorator = 0;
        for (int i = 0; i < Integer.min(d1.size(), d2.size()); i++) {
            numorator += Math.abs(d1.get(i).getNumberPrecincts() - d2.get(i).getNumberPrecincts());
        }
        return numorator / denominator;
    }

    private static double gillObjectiveFunction(District d1, District d2) {

        return (double) numberCommonPrecincts(d1, d2) / Integer.max(d1.getNumberPrecincts(), d2.getNumberPrecincts());
    }

    private static long numberCommonPrecincts(District d1, District d2) {

        return d1.getPrecincts()
                .stream()
                .map(Precinct::getPrecinctId)
                .filter(pId -> d2.getPrecinctIds().contains(pId))
                .count();
    }
}
