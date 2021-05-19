package edu.stonybrook.redistricting.lemonkeredistricting.service;

import edu.stonybrook.redistricting.lemonkeredistricting.models.*;
import edu.stonybrook.redistricting.lemonkeredistricting.repo.DistrictingSummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ConstraintsBuilder {

    @Autowired
    private DistrictingSummaryRepository districtingSummaryRepository;

    public Map<CompactnessType, Object> buildCompactnessConstraintsArray(Long jobId) {

        Map<CompactnessType, Object> constraintsMap = new HashMap<>();
        List<DistrictingSummary> summaries = districtingSummaryRepository.findDistrictingSummaryByJobId(jobId);

        for (CompactnessType compactnessType : CompactnessType.values()) {

            List<List<Long>> constraintList = new ArrayList<>(Collections.nCopies(100, null));

            System.out.println(constraintList.size());

            for (DistrictingSummary districtingSummary : summaries) {

                int index = (int) (100 * districtingSummary.getCompactnessByCompactnessType(compactnessType));
                long id = districtingSummary.getDistrictingSummaryId();

                List<Long> indexArray = constraintList.get(index);
                if (indexArray == null)
                    indexArray = new ArrayList<>();
                indexArray.add(id);
                constraintList.set(index, indexArray);
            }

            constraintsMap.put(compactnessType, constraintList);
        }

        return constraintsMap;
    }


    public Map<PopulationType, Object> buildPopulationConstraintsArray(Long jobId) {

        Map<PopulationType, Object> constraintsMap = new HashMap<>();

        for (PopulationType populationType : PopulationType.values()) {

            List<List<Long>> constraintList = new ArrayList<>(100);
            for (DistrictingSummary districtingSummary : districtingSummaryRepository.findDistrictingSummaryByJobId(jobId)) {

                int index = (int) (100 * districtingSummary.getPopulationDifferenceByPopulationType(populationType));
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

    public List<DistrictingSummary> constrainJob(List<DistrictingSummary> districtingSummaries,
                                                 List<Incumbent> incumbents,
                                                 CompactnessType compactnessType,
                                                 Double compactnessValue,
                                                 Integer mmDistricts,
                                                 Ethnicity ethnicity,
                                                 Double mmThreshold,
                                                 PopulationType populationType,
                                                 Double populationValue) {


        return districtingSummaries
                .stream()
                .peek(System.out::println)
                .filter(summary -> summary.getMMDistrictsByEthnicity(ethnicity, mmThreshold) >= mmDistricts)
                .filter(summary -> summary.getCompactnessByCompactnessType(compactnessType) >= compactnessValue)
                .filter(summary -> summary.getPopulationDifferenceByPopulationType(populationType) <= populationValue)
                .collect(Collectors.toList());
    }

}
