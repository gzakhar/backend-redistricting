package edu.stonybrook.redistricting.lemonkeredistricting.service;

import edu.stonybrook.redistricting.lemonkeredistricting.models.Districting;
import edu.stonybrook.redistricting.lemonkeredistricting.models.DistrictingScore;

import java.util.ArrayList;
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
            //objective function should be a double based on a function
        List<DistrictingScore> districtingScores = new ArrayList<>();
        for (Integer districtingID : districtingIds){
            // create districtingScore
        }

//        Should return a list of class.DistrictingScore
        return null;
    }

    private Long objectiveFunctionScore (Integer districtingId,
                                         Double PopulationEquality,
                                         Double SplitCounties,
                                         Double DeviationFromAverage,
                                         Double DeviationFromEnacted,
                                         Double DeviationFromEnactedPopulation,
                                         Double Compactness,
                                         Double PoliticalFairness){
        //get the districting from districtingid
        //need to access the enacted districting to get the details
        //need to know what population type to calculate popequality
        //remove political fairness, we are not implementing that
        //need to access the average districting
        //need to figure out how many split counties are in the districting
        return null;
    }

}
