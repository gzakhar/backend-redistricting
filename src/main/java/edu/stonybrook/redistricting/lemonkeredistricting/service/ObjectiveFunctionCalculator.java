package edu.stonybrook.redistricting.lemonkeredistricting.service;

import edu.stonybrook.redistricting.lemonkeredistricting.models.*;
import edu.stonybrook.redistricting.lemonkeredistricting.repo.DistrictingRepository;
import edu.stonybrook.redistricting.lemonkeredistricting.repo.DistrictingSummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ObjectiveFunctionCalculator {

    @Autowired
    DistrictingSummaryRepository districtingSummaryRepository;

    @Autowired
    DistrictingRepository districtingRepository;

    //    Should calculate the Objectve function score for all districtings id's that are provided.
    public List<DistrictingScore> calculateObjectiveFunction(List<Integer> districtingIds,
                                                             PopulationType populationType,
                                                             Double PopulationEqualityWeight,
                                                             Double DeviationFromAveragePopulationWeight,
                                                             Double DeviationFromAverageAreaWeight,
                                                             Double DeviationFromEnactedPopulationWeight,
                                                             Double DeviationFromEnactedAreaWeight,
                                                             CompactnessType compactnessType,
                                                             Double CompactnessWight
    ) {

        List<DistrictingSummary> filteredDistrictings = districtingSummaryRepository
                .findAllById((Iterable<Long>) districtingIds.iterator());

//        find using list of provided Ids.
        Districting average = new Districting();

//        find by seeing to which state the districtings belong to.
        Districting enaceted = new Districting();

        List<DistrictingScore> districtingScores = new ArrayList<>();

        for (DistrictingSummary districtingSummary : filteredDistrictings) {

            Districting current = districtingRepository.findById(districtingSummary.getDistrictingSummaryId()).orElse(null);

            Double ofScore = objectiveFunctionScore(
                    PopulationEqualityWeight,
                    districtingSummary.getPopulationEqualityByPopulationType(populationType),
                    DeviationFromAveragePopulationWeight,
                    average.getPopulationDeviationFromReference(current),
                    DeviationFromAverageAreaWeight,
                    average.getAreaDeviationFromReference(current),
                    DeviationFromEnactedPopulationWeight,
                    enaceted.getPopulationDeviationFromReference(current),
                    DeviationFromEnactedAreaWeight,
                    enaceted.getAreaDeviationFromReference(current),
                    CompactnessWight,
                    districtingSummary.getCompactnessByCompactnessType(compactnessType));

            districtingScores.add(new DistrictingScore(districtingSummary.getDistrictingSummaryId(), ofScore));
        }

        return districtingScores;
    }

    private Double objectiveFunctionScore(Double PopulationEqualityWeight,
                                          Double PopulationEqualityValue,
                                          Double DeviationFromAveragePopulationWeight,
                                          Double DeviationFromAveragePopulationValue,
                                          Double DeviationFromAverageAreaWeight,
                                          Double DeviationFromAverageAreaValue,
                                          Double DeviationFromEnactedPopulationWeight,
                                          Double DeviationFromEnactedPopulationValue,
                                          Double DeviationFromEnactedAreaWeight,
                                          Double DeviationFromEnactedAreaValue,
                                          Double CompactnessWight,
                                          Double CompactnessValue
    ) {

        //get the districting from districtingid
        //need to access the enacted  districting to get the details
        //need to know what population type to calculate popequality
        //remove political fairness, we are not implementing that
        //need to access the average districting

        double PopulationEqualityWeightValue = PopulationEqualityWeight * PopulationEqualityValue;
        double DeviationFromAveragePopulationWeightValue = DeviationFromAveragePopulationWeight * DeviationFromAveragePopulationValue;
        double DeviationFromAverageAreaWeightValue = DeviationFromAverageAreaWeight * DeviationFromAverageAreaValue;
        double DeviationFromEnactedPopulationWeightValue = DeviationFromEnactedPopulationWeight * DeviationFromEnactedPopulationValue;
        double DeviationFromEnactedAreaWeightValue = DeviationFromEnactedAreaWeight * DeviationFromEnactedAreaValue;
        double CompactnessWightValue = CompactnessWight * CompactnessValue;

        return PopulationEqualityWeightValue +
                DeviationFromAveragePopulationWeightValue +
                DeviationFromAverageAreaWeightValue +
                DeviationFromEnactedPopulationWeightValue +
                DeviationFromEnactedAreaWeightValue +
                CompactnessWightValue;
    }

}
