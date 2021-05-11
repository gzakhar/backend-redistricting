package edu.stonybrook.redistricting.lemonkeredistricting.batch;

import edu.stonybrook.redistricting.lemonkeredistricting.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DistrictingProcessor implements ItemProcessor<Districting, DistrictingSummary> {

    private static final Logger log = LoggerFactory.getLogger(DistrictingProcessor.class);

    @Override
    public DistrictingSummary process(final Districting districting) throws Exception {
        long id = districting.getDistrictingId();
        int count = districting.getDistricts().size();

        DistrictingSummary districtingSummary = new DistrictingSummary(id, count);

        log.info("Converting (" + districting + ") into (" + districtingSummary + ")");
        System.out.println(districtingSummary);

////        number of MMdistricts.
//        Map<Ethnicity, Integer> mmDistricts = new HashMap<>();
//        for (Ethnicity e : Ethnicity.values()) {
//            mmDistricts.put(e, districting.getMaxMMDistricts(e));
//        }
//
////        Population Equality. (percent difference between the most populous district and the least populous district)
//        Map<PopulationType, Boolean> populationAvailability = districting.getPopulationTypeAvailablability();
//        Map<PopulationType, Double> populationEquality = new HashMap<>();
//        for (PopulationType populationType : PopulationType.values()) {
//
//            List<District> districtsOrderedList = districting.getDistrictsPopulationDesc(populationType);
//            if (populationAvailability.get(populationType)) {
//                Double popDifference = ((double)
//                        districtsOrderedList.get(0)
//                                .getTotalPopulation(populationType)
//                        - districtsOrderedList.get(districtsOrderedList.size() - 1)
//                        .getTotalPopulation(populationType))
//                        / districting.getTotalPopulation(populationType);
//
//                populationEquality.put(populationType, popDifference);
//            } else {
//                populationEquality.put(populationType, null);
//            }
//        }
////        Incumbent Protection


        return districtingSummary;
    }

}
