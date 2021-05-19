package edu.stonybrook.redistricting.lemonkeredistricting.batch;

import edu.stonybrook.redistricting.lemonkeredistricting.models.*;
import edu.stonybrook.redistricting.lemonkeredistricting.service.GeometryCalculation;
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
//        long start = System.currentTimeMillis();
        Long numberOfDistricts = (long) districting.getNumberDistricts();
        if (numberOfDistricts == 0) return null;
        districtingSummary.setDistrictCount(numberOfDistricts);
//        System.out.println("dist count: " + (System.currentTimeMillis() - start));

//        start = System.currentTimeMillis();
//        Map of Compactness Measures.
        districtingSummary.setGeometricCompactness(GeometryCalculation.getGeometricCompactness(districting));
//        System.out.println("compactness : " + (System.currentTimeMillis() - start));


//        start = System.currentTimeMillis();
//        Map of mm Districts by Ethnicity.
        for (Ethnicity e : Ethnicity.values()) {

            switch (e) {
                case WHITE:
                    districtingSummary.setMmWhite((long) districting.getMMDistrictCount(e));
                case BLACK:
                    districtingSummary.setMmBlack((long) districting.getMMDistrictCount(e));
                case HISPANIC:
                    districtingSummary.setMmHispanic((long) districting.getMMDistrictCount(e));
                case ASIAN:
                    districtingSummary.setMmAsian((long) districting.getMMDistrictCount(e));
                case AMERICAN_INDIAN:
                    districtingSummary.setMmAmind((long) districting.getMMDistrictCount(e));
                case OTHER:
                    districtingSummary.setMmOther((long) districting.getMMDistrictCount(e));
            }
        }
//        System.out.println("mm Districts : " + (System.currentTimeMillis() - start));


        /** this calculating id obeolete because I will populate this with a query*/
////        Population Difference Map. (percent difference between the most populous district and the least populous district)
//        Map<PopulationType, Double> populationDifference = new HashMap<>();
//
//        for (PopulationType populationType : PopulationType.values()) {
//
//            List<District> districtsOrderedList = districting.orderDistrictsByPopulationType(populationType);
//            if (districting.getPopulationTypeAvailablability().get(populationType)) {
//
////                Find the population difference between most and least populous districts.
//                double mostPopulousPopulation = districtsOrderedList
//                        .get(0)
//                        .getTotalPopulation(populationType);
//                double leastPopulousPopulaton = districtsOrderedList
//                        .get(districtsOrderedList.size() - 1)
//                        .getTotalPopulation(populationType);
//                double totalPopulation = districting.getTotalPopulation(populationType);
//
//                double popDifference = Math.abs(mostPopulousPopulation - leastPopulousPopulaton) / totalPopulation;
//
//                populationDifference.put(populationType, popDifference);
//            } else {
//                populationDifference.put(populationType, null);
//            }
//        }
//
//        districtingSummary.setTotalPopulation(populationDifference.getOrDefault(PopulationType.TOTAL_POPULATION, null));
//        districtingSummary.setVaPopulation(populationDifference.getOrDefault(PopulationType.VOTING_AGE_POPULATION, null));
//        districtingSummary.setCvaPopulation(populationDifference.getOrDefault(PopulationType.CITIZEN_VOTING_AGE_POPULATION, null));


////        Population Equality Map. Using Maningly
//        Map<PopulationType, Double> populationEquality = new HashMap<>();
//
//
//        List<District> districtsOrderedList = (List<District>) districting.getDistricts();
//        for (PopulationType populationType : PopulationType.values()) {
//
//            if (districting.getPopulationTypeAvailablability().get(populationType)) {
//
//                Integer idealPopulation = districting.getTotalPopulation(populationType) / districtsOrderedList.size();
//                double sum = 0.0;
//                for (District district : districtsOrderedList) {
//                    double pop = district.getTotalPopulation(populationType);
//                    sum += Math.pow((double)(pop / idealPopulation) - 1, 2);
//                }
//                Double populationEqualityScore = Math.sqrt(sum);
//                populationEquality.put(populationType, populationEqualityScore);
//            } else {
//                populationEquality.put(populationType, null);
//            }
//        }
//
//        districtingSummary.setTotalPopulationEquality(populationEquality.getOrDefault(PopulationType.TOTAL_POPULATION, null));
//        districtingSummary.setVaPopulationEquality(populationEquality.getOrDefault(PopulationType.VOTING_AGE_POPULATION, null));
//        districtingSummary.setCvaPopulationEquality(populationEquality.getOrDefault(PopulationType.CITIZEN_VOTING_AGE_POPULATION, null));

//        start = System.currentTimeMillis();
//        EqualPopulationMeasure only for Population type "TOTAL_POPULATION"
        double idealPopulation = districting.getTotalPopulation(PopulationType.TOTAL_POPULATION) / (double) numberOfDistricts;
        double res = districting.getDistricts().stream()
                .map(district -> (double) district.getTotalPopulation(PopulationType.TOTAL_POPULATION))
                .reduce(0.0, (f, s) -> f + Math.pow((s / idealPopulation) - 1, 2));
        districtingSummary.setTotalPopulationEquality(res);
//        System.out.println("equal population : " + (System.currentTimeMillis() - start));

        return districtingSummary;
    }

}
