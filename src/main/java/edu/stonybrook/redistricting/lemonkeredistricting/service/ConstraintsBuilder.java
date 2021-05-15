package edu.stonybrook.redistricting.lemonkeredistricting.service;

import edu.stonybrook.redistricting.lemonkeredistricting.models.CompactnessType;
import edu.stonybrook.redistricting.lemonkeredistricting.models.DistrictingSummary;
import edu.stonybrook.redistricting.lemonkeredistricting.models.PopulationType;
import edu.stonybrook.redistricting.lemonkeredistricting.repo.DistrictingSummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ConstraintsBuilder {

    @Autowired
    static DistrictingSummaryRepository districtingSummaryRepository;

    public static Map<CompactnessType, Object> buildCompactnessConstraintsArray(Long jobId) {

        Map<CompactnessType, Object> constraintsMap = new HashMap<>();

        for (CompactnessType compactnessType: CompactnessType.values()){

            List<List<Long>> constraintList = new ArrayList<>(100);
            for (DistrictingSummary districtingSummary : districtingSummaryRepository.findDistrictingSummaryByJobId(jobId)) {

                int index = (int) (100 * districtingSummary.getCompactnessByCompactnessType(compactnessType));
                long id = districtingSummary.getDistrictingSummaryId();

                List<Long> indexArray = constraintList.get(index);
                if (indexArray == null)
                    indexArray = new ArrayList<>();
                indexArray.add(id);
                constraintList.add(index, indexArray);
            }

            constraintsMap.put(compactnessType, constraintList);
        }

        return constraintsMap;
    }


    public static Map<PopulationType, Object> buildPopulationConstraintsArray(Long jobId) {

        Map<PopulationType, Object> constraintsMap = new HashMap<>();

        for (PopulationType populationType: PopulationType.values()){

            List<List<Long>> constraintList = new ArrayList<>(100);
            for (DistrictingSummary districtingSummary : districtingSummaryRepository.findDistrictingSummaryByJobId(jobId)) {

                int index = (int) (100 * districtingSummary.getPopulationByPopulationType(populationType));
                long id = districtingSummary.getDistrictingSummaryId();

                List<Long> indexArray = constraintList.get(index);
                if (indexArray == null)
                    indexArray = new ArrayList<>();
                indexArray.add(id);
                constraintList.add(index, indexArray);
            }

            constraintsMap.put(populationType, constraintList);
        }

        return constraintsMap;
    }

}
