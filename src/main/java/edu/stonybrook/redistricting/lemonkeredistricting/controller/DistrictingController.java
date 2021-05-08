package edu.stonybrook.redistricting.lemonkeredistricting.controller;

import edu.stonybrook.redistricting.lemonkeredistricting.models.*;
import edu.stonybrook.redistricting.lemonkeredistricting.repo.*;
import edu.stonybrook.redistricting.lemonkeredistricting.service.GeometryCalculation;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/lemonke")
public class DistrictingController {

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private DistrictingRepository districtingRepository;

    @Autowired
    private GeometryCalculation geometryCalculation;

    @GetMapping("/districtings")
    public List<Districting> getDistrictings() {

        return districtingRepository.findAll();
    }

    @GetMapping("/districtings/{districtingId}")
    public Districting getDistrictingById(HttpSession httpSession, @PathVariable Long districtingId) {

        return districtingRepository.findById(districtingId).orElse(null);
    }

//    TODO: session not available.
//    @GetMapping("/districtings/{districtingId}")
//    public Districting getDistrictingById(HttpSession httpSession, @PathVariable Long districtingId) {
//
//        Districting districting = (Districting) httpSession.getAttribute("current-districting");
//        if(districting == null){
//            System.out.println("No districting districtingId: " + districtingId + " cached");
//            districting = districtingRepository.findById(districtingId).orElse(null);
//        }
//
//        return districting;
//    }

    @GetMapping("/districtings/{districtingId}/districts")
    public List<District> getDistrictByDistrictingId(@PathVariable Long districtingId) {

        return districtRepository.findAllByDistrictingId(districtingId);
    }

    @GetMapping("/districtings/{districtingId}/max-mm-districts/{ethnicity}")
    public Integer getMaxMMDistricts(@PathVariable Long districtingId, @PathVariable Ethnicity ethnicity) {

        Optional<Districting> districting = districtingRepository.findById(districtingId);

        return districting.map(d -> d.getMaxMMDistricts(ethnicity)).orElse(null);
    }

    @GetMapping("/districtings/{districtingId}/population-type-availability")
    public Map<PopulationType, Boolean> getPopulationTypeAvailability(@PathVariable Long districtingId) {

        Optional<Districting> districting = districtingRepository.findById(districtingId);

        return districting.map(Districting::getPopulationTypeAvailablability).orElse(null);
    }

    @PostMapping("/districtings/{districtingId}")
    public String setDistricting(HttpSession httpSession, @PathVariable Long districtingId) {

        Districting districting = districtingRepository.findById(districtingId).orElse(null);
        if (districting != null) {
            httpSession.setAttribute("current-districting", districting);
            return "Succeessful setAttribute districtingId: " + districtingId;
        }

        throw new IllegalArgumentException("Succeessful setAttribute districtingId: " + districtingId);
    }

    @GetMapping("/districtings/{districtingId}/geometry")
    public JSONObject getDistrictingGeometry(@PathVariable Long districtingId) throws IOException, ParseException {


        return geometryCalculation.calculateDistrictingGeometry(districtingId);
    }

//    @GetMapping("/getConstraintCountIncumbent")
//    public List<Map<String, Object>> getConstraintCountIncumbent(int[] incumbents, int jobId) {
//        return null;
//    }

//    @GetMapping("/constraint")
//    public Map<String, List<Integer>> getConstraintArrays(int jobId) {
//        List<Map<String, List<Integer>>> list = new ArrayList<>();
//        Map<String, List<Integer>> map = new HashMap<>();
//
//        map.put("population_area", List.of(1, 2, 3, 4, 5, 6, 7));
//        map.put("population_polsby_popper", List.of(1, 2, 3, 4, 5, 6, 7));
//        map.put("population_fatness", List.of(1, 2, 3, 4, 5, 6, 7));
//
//        return map;
//    }

//    @GetMapping("/constrainedData")
//    public Map<String, Object> getConstrainedData(String[] incumbents,
//                                                  String compactnessType,
//                                                  double compactnessVal,
//                                                  int mmDistricts,
//                                                  String populationType,
//                                                  double populationVal) {
//        return null;
//    }

//    @GetMapping("/getConstrainedRankedDistrictings")
//    public Map<String, Object> getConstrainedRankedDistrictings(String[] incumbents,
//                                                                String compactnessType,
//                                                                double compactnessVal,
//                                                                int mmDistricts,
//                                                                String populationType,
//                                                                double populationVal,
//                                                                double populationEquality,
//                                                                double splitCounty,
//                                                                double deviationFromEnacted,
//                                                                double deviationFromAverage,
//                                                                double compactness,
//                                                                double populationFairness) {
//        return null;
//    }
}
