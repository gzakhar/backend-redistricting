package edu.stonybrook.redistricting.lemonkeredistricting.service;

import edu.stonybrook.redistricting.lemonkeredistricting.models.DistrictingScore;

import java.util.List;

public class ObjectiveFunctionCalculator {


//    Should calculate the Objectve function score for all districtings id's that are provided.
    public static List<DistrictingScore> calculateObjectiveFunction(
            List<Integer> districtingIds,
            Double PopulationEquality,
            Double SplitCounties,
            Double DeviationFromAverage,
            Double DeviationFromEnacted,
            Double DeviationFromEnactedPopulation,
            Double Compactness,
            Double PoliticalFairness
    ) {


//        Should return a list of class.DistrictingScore
        return null;
    }

}
