package edu.stonybrook.redistricting.lemonkeredistricting.service;

import edu.stonybrook.redistricting.lemonkeredistricting.models.DistrictingScore;
import edu.stonybrook.redistricting.lemonkeredistricting.models.DistrictingSummary;
import edu.stonybrook.redistricting.lemonkeredistricting.repo.DistrictingSummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ObjectiveFunctionCalculator {

    @Autowired
    DistrictingSummaryRepository districtingSummaryRepository;

    //    Should calculate the Objectve function score for all districtings id's that are provided.
    public List<DistrictingScore> calculateObjectiveFunction(
    ) {

//        find all by districtingIds.
        List<DistrictingSummary> districtingSummaries = districtingSummaryRepository
                .findAllById((Iterable<Long>) districtingIds.iterator());

        List<DistrictingScore> districtingScores = new ArrayList<>();
        for (DistrictingSummary districtingSummary : districtingSummaries) {

            DistrictingScore score = calculateObjScore(
                    districtingSummary,
                    null,
                    null,
                    populationEquality,
                    splitCounties,
                    deviationFromAverage,
                    deviationFromEnacted,
                    deviationFromEnactedPopulation,
                    compactness,
                    politicalFairness);

            System.out.println(score);
            districtingScores.add(score);
        }


        return districtingScores;
    }

//    Implement this method.
//    This method will need access to enacted and average,
//    this isnt done yet, but pretent there is a parameter that has the id of the average and enacted
    private DistrictingScore calculateObjScore(DistrictingSummary districtingSummary,
                                               Long enactedId,
                                               Long averageId,
                                               Double PopulationEquality,
                                               Double SplitCounties,
                                               Double DeviationFromAverage,
                                               Double DeviationFromEnacted,
                                               Double DeviationFromEnactedPopulation,
                                               Double Compactness,
                                               Double PoliticalFairness) {



        return null;
    }

}
