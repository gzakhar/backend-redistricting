package edu.stonybrook.redistricting.lemonkeredistricting.service;

import edu.stonybrook.redistricting.lemonkeredistricting.models.Districting;
import edu.stonybrook.redistricting.lemonkeredistricting.models.DistrictingScore;
import edu.stonybrook.redistricting.lemonkeredistricting.models.DistrictingSummary;
import edu.stonybrook.redistricting.lemonkeredistricting.repo.DistrictingSummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObjectiveFunctionCalculator {

    @Autowired
    DistrictingSummaryRepository districtingSummaryRepository;

    //    Should calculate the Objectve function score for all districtings id's that are provided.
    public static List<DistrictingScore> calculateObjectiveFunction(List<Integer> districtingIds,
                                                                    Double PopulationEqualityWeight,
                                                                    Double DeviationFromAveragePopulationWeight,
                                                                    Double DeviationFromAverageAreaWeight,
                                                                    Double DeviationFromEnactedPopulationWeight,
                                                                    Double DeviationFromEnactedAreaWeight,
                                                                    Double CompactnessWight
    ) {

//        find using list of provided Ids.
//        Districting average = new Districting()

//        find by seeing to which state the districtings belong to.
//        Districting enaceted = new Districting();



//        Should return a list of class.DistrictingScore
        return null;
    }

    private Double objectiveFunctionScore(Integer districtingId,
                                          Double PopulationEqualityWeight,
                                          Double DeviationFromAveragePopulationWeight,
                                          Double DeviationFromAverageAreaWeight,
                                          Double DeviationFromEnactedPopulationWeight,
                                          Double DeviationFromEnactedAreaWeight,
                                          Double CompactnessWight,
                                          DistrictingSummary enacted,
                                          DistrictingSummary average
    ) {

        //get the districting from districtingid
        //need to access the enacted  districting to get the details
        //need to know what population type to calculate popequality
        //remove political fairness, we are not implementing that
        //need to access the average districting
        return null;
    }

}
