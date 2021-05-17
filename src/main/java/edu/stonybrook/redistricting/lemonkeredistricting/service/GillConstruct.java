package edu.stonybrook.redistricting.lemonkeredistricting.service;

import edu.stonybrook.redistricting.lemonkeredistricting.models.District;
import edu.stonybrook.redistricting.lemonkeredistricting.models.Districting;
import edu.stonybrook.redistricting.lemonkeredistricting.models.PopulationType;
import edu.stonybrook.redistricting.lemonkeredistricting.models.Precinct;
import edu.stonybrook.redistricting.lemonkeredistricting.repo.DistrictingRepository;
import edu.stonybrook.redistricting.lemonkeredistricting.repo.DistrictingSummaryRepository;
import org.jgrapht.alg.matching.MaximumWeightBipartiteMatching;
import org.jgrapht.generate.CompleteBipartiteGraphGenerator;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultUndirectedGraph;
import org.jgrapht.graph.DefaultUndirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.jgrapht.*;

public class GillConstruct {

    @Autowired
    static DistrictingRepository districtingRepository;

    public static List<District> reorderDistricts(Districting reorderDistricting, Districting reference) {

        double denominator = reorderDistricting.getNumberPrecincts().doubleValue();
        List<District> refrenceRecombination = reference.getDistrictOrderPopulation();

        List<List<District>> reordering = districtRecombinations(reorderDistricting)
                .stream()
                .sorted(Comparator.comparingInt(r -> ((int) gillObjectiveFunction(denominator, r, refrenceRecombination))))
                .collect(Collectors.toList());

        System.out.println(reordering);

        return reordering.get(reordering.size() - 1);
    }

    public static double populationDifferenceFromDistricting(Districting districting, Districting reference) {

        return populationDifference(
                districting.getTotalPopulation(PopulationType.TOTAL_POPULATION),
                reference.getDistrictOrderPopulation(),
                districting.getDistrictsOrder(reference));
    }

    public static double areaDifferenceFromDistricting(Districting districting, Districting reference){

        return areaDifference(
                districting.getNumberPrecincts(),
                reference.getDistrictOrderPopulation(),
                districting.getDistrictsOrder(reference));
    }

    private static List<List<District>> districtRecombinations(Districting districting) {
        Set<District> current = (Set<District>) districting.getDistricts();
        Districting enacted = districtingRepository.findEnactedByDistrictingId(districting.getDistrictingId()).get();
        Set<District> enactedDistricts = (Set<District>) enacted.getDistricts();

        CompleteBipartiteGraphGenerator<District, DefaultWeightedEdge> graphGenerator = new CompleteBipartiteGraphGenerator<>(
                enactedDistricts,
                current
        );
        DefaultUndirectedWeightedGraph<District, DefaultWeightedEdge> graph = new DefaultUndirectedWeightedGraph<>(DefaultWeightedEdge.class);
        graphGenerator.generateGraph(graph);

        MaximumWeightBipartiteMatching<District, DefaultWeightedEdge> matchings = new MaximumWeightBipartiteMatching<>(
                graph,
                enactedDistricts,
                current);

        Set<DefaultWeightedEdge> matchingEdges = matchings.getMatching().getEdges();
        //        //each edge is connecting enacted district to corresponding current district
        //        //number accordingly via population order from enacted
        List<District> enactedPopulationOrdering = enacted.getDistrictOrderPopulation();
        for (DefaultWeightedEdge edge : matchingEdges){
            District enactedDistrict = (District) edge.getSource()
        }

        List<List<District>> recombination = new ArrayList();
        recombination.add(districting.getDistrictOrderPopulation());

        return recombination;
    }

    private static double gillObjectiveFunction(Double denominator, List<District> d1, List<District> d2) {

        double intersection = 0;
        for (int i = 0; i < d1.size(); i++) {
            intersection += numberCommonPrecincts(d1.get(i), d2.get(i));
        }
        return intersection / denominator;
    }

    private static long numberCommonPrecincts(District d1, District d2) {

        return d1.getPrecincts()
                .stream()
                .map(Precinct::getPrecinctId)
                .filter(pId -> d2.getPrecinctIds().contains(pId))
                .count();
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
}
