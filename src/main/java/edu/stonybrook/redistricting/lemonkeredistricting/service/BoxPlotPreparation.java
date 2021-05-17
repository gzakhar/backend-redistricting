package edu.stonybrook.redistricting.lemonkeredistricting.service;

import edu.stonybrook.redistricting.lemonkeredistricting.models.District;
import edu.stonybrook.redistricting.lemonkeredistricting.models.Districting;
import edu.stonybrook.redistricting.lemonkeredistricting.models.Ethnicity;
import edu.stonybrook.redistricting.lemonkeredistricting.models.PopulationType;
import edu.stonybrook.redistricting.lemonkeredistricting.repo.DistrictingRepository;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.JettyHttpHandlerAdapter;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class BoxPlotPreparation {

    @Autowired
    private DistrictingRepository districtingRepository;

    private static double getMinorityPercentage(District district, PopulationType populationType, Ethnicity ethnicity)  {
        return district.getPopulation(ethnicity, populationType)/district.getTotalPopulation(populationType);
    }

    //quicksort the district with selected minority population
    private static List<Double> getSortedPopulationPercentages(Districting districting, PopulationType populationType, Ethnicity ethnicity) {
        Collection<District> districts = districting.getDistricts();

        List<Double> unsortedPercentages = new ArrayList<>();

        for(District district : districts) {
            for(Ethnicity e : Ethnicity.values()) {
                double percentage = getMinorityPercentage(district, populationType, e);
                unsortedPercentages.set(e.ordinal(), percentage);
            }
        }
        unsortedPercentages.sort(Comparator.comparingDouble(Double::doubleValue));

        return unsortedPercentages;
    }

    public static JSONObject prepareBox(Collection<Districting> constrainedDistrictings, PopulationType populationType) {
        JSONArray xData = new JSONArray();
        JSONArray yData = new JSONArray();
        yData.add(new JSONArray());

        //prepare xData
        for(int i = 1; i <= constrainedDistrictings.iterator().next().getDistricts().size(); i++) {
            String name = "District "+i;
            xData.add(name);

        }

        //prepare yData
        for(int i = 0; i <= Ethnicity.values().length; i++) {
            yData.add(new JSONArray());
        }

        for(Districting districting : constrainedDistrictings) {
            for(Ethnicity ethnicity : Ethnicity.values()) {

                List<Double> sortedPopulationPercentages = getSortedPopulationPercentages(districting, populationType, ethnicity);


                for(int i = 0; i < sortedPopulationPercentages.size(); i++) {
                    ((JSONArray)yData.get(ethnicity.ordinal())).add(sortedPopulationPercentages.get(i));
                }
            }
        }

        JSONObject output = new JSONObject();
        output.put("xData", xData);
        output.put("yData", yData);

        return output;
    }

    public static JSONObject preparePlot(Districting selectedDistricting, PopulationType populationType) {
        JSONArray xData = new JSONArray();
        JSONArray yData = new JSONArray();

        for(int i = 1; i <= selectedDistricting.getDistricts().size(); i++) {
            String name = "District "+i;
            xData.add(name);
        }

        for(int i = 0; i <= Ethnicity.values().length; i++) {
            yData.add(new JSONArray());
        }

        for(Ethnicity ethnicity : Ethnicity.values()) {

            List<Double> sortedPopulationPercentages = getSortedPopulationPercentages(selectedDistricting, populationType ,ethnicity);

            for(int i = 0; i < sortedPopulationPercentages.size(); i++) {
                ((JSONArray)yData.get(ethnicity.ordinal())).add(sortedPopulationPercentages.get(i));
            }
        }

        JSONObject output = new JSONObject();
        output.put("xData", xData);
        output.put("yData", yData);

        return output;
    }

}
