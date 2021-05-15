package edu.stonybrook.redistricting.lemonkeredistricting.service;

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
    public List<DistrictingScore> calculateObjectiveFunction(
            List<Integer> districtingIds,
            Double PopulationEquality,
            Double SplitCounties,
            Double DeviationFromAverage,
            Double DeviationFromEnacted,
            Double DeviationFromEnactedPopulation,
            Double Compactness,
            Double PoliticalFairness
    ) {

//        find all by districtingIds.
        List<DistrictingSummary> districtingSummaries = districtingSummaryRepository
                .findAllById((Iterable<Long>) districtingIds.iterator());

//        work on districtingSummaries.

//        Should return a list of class.DistrictingScore
        return null;
    }

}
