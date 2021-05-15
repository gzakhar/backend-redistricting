package edu.stonybrook.redistricting.lemonkeredistricting.batch;

import edu.stonybrook.redistricting.lemonkeredistricting.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DistrictingProcessor implements ItemProcessor<Districting, DistrictingSummary> {

    private static final Logger log = LoggerFactory.getLogger(DistrictingProcessor.class);

    @Override
    public DistrictingSummary process(final Districting districting) throws Exception {

        DistrictingSummary districtingSummary = new DistrictingSummary();

//        id.
        Long id = districting.getDistrictingId();
        districtingSummary.setDistrictingSummaryId(id);

//        district count.
        Long count = (long) districting.getDistricts().size();
        districtingSummary.setDistrictCount(count);

//        Map of Compactness Measures.
        districtingSummary.setGeometricCompactness(districting.getGeometricCompactness());
        districtingSummary.setGraphCompactness(null);
        districtingSummary.setPopulationCompactness(null);


//        Map of mm Districts by Ethnicity.
        Map<Ethnicity, Integer> mmDistricts = new HashMap<>();
        for (Ethnicity e : Ethnicity.values()) {

            mmDistricts.put(e, districting.getMMDistrictCount(e));
        }
        districtingSummary.setMmWhite((long) mmDistricts.getOrDefault(Ethnicity.WHITE, null));
        districtingSummary.setMmBlack((long) mmDistricts.getOrDefault(Ethnicity.BLACK, null));
        districtingSummary.setMmHispanic((long) mmDistricts.getOrDefault(Ethnicity.HISPANIC, null));
        districtingSummary.setMmAsian((long) mmDistricts.getOrDefault(Ethnicity.ASIAN, null));
        districtingSummary.setMmAmind((long) mmDistricts.getOrDefault(Ethnicity.AMERICAN_INDIAN, null));
        districtingSummary.setMmOther((long) mmDistricts.getOrDefault(Ethnicity.OTHER, null));


//        Population Equality Map. (percent difference between the most populous district and the least populous district)
        Map<PopulationType, Double> populationEquality = new HashMap<>();

        for (PopulationType populationType : PopulationType.values()) {

            List<District> districtsOrderedList = districting.orderDistrictsByPopulationType(populationType);
            if (districting.getPopulationTypeAvailablability().get(populationType)) {

//                Find the population difference between most and least populous districts.
                double mostPopulousPopulation = districtsOrderedList
                        .get(0)
                        .getTotalPopulation(populationType);
                double leastPopulousPopulaton = districtsOrderedList
                        .get(districtsOrderedList.size() - 1)
                        .getTotalPopulation(populationType);
                double totalPopulation = districting.getTotalPopulation(populationType);


                double popDifference = (mostPopulousPopulation - leastPopulousPopulaton) / totalPopulation;

                populationEquality.put(populationType, popDifference);
            } else {
                populationEquality.put(populationType, null);
            }
        }

        districtingSummary.setTotalPopulation(populationEquality.getOrDefault(PopulationType.TOTAL_POPULATION, null));
        districtingSummary.setVaPopulation(populationEquality.getOrDefault(PopulationType.VOTING_AGE_POPULATION, null));
        districtingSummary.setCvaPopulation(populationEquality.getOrDefault(PopulationType.CITIZEN_VOTING_AGE_POPULATION, null));

        log.info("Converted (" + id + ")");

        return districtingSummary;
    }

}
