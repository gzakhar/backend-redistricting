package edu.stonybrook.redistricting.lemonkeredistricting.controller;

import edu.stonybrook.redistricting.lemonkeredistricting.models.*;
import edu.stonybrook.redistricting.lemonkeredistricting.repo.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.Cacheable;


import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@RequestMapping("/lemonke")
public class DistrictingController {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private StateSummaryRepository stateSummaryRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private DistrictingRepository districtingRepository;

    @Autowired
    private IncumbentRepository incumbentRepository;

    @Autowired
    private PrecinctRepository precinctRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @GetMapping("/test")
    public String home() {
        return "Hello World!";
    }

    @GetMapping("/set-job/{id}")
    public void setCurrentJob(HttpSession httpSession, @PathVariable long id) {

        httpSession.setAttribute("currentJob", jobRepository.findById(id));
    }

    @GetMapping("/districtings/{id}")
    public Optional<Districting> getDistrictingById(@PathVariable long id) {

        return districtingRepository.findById(id);
    }

    @GetMapping("/districtings")
    public List<Districting> getDistrictings() {

        return (List<Districting>) districtingRepository.findAll();
    }

    @GetMapping("/getConstraintCountIncumbent")
    public List<Map<String, Object>> getConstraintCountIncumbent(int[] incumbents, int jobId) {
//      int
        return null;
    }

    @GetMapping("/constraint")
    public Map<String, List<Integer>> getConstraintArrays(int jobId) {
        List<Map<String, List<Integer>>> list = new ArrayList<>();
        Map<String, List<Integer>> map = new HashMap<>();

        map.put("population_area", List.of(1, 2, 3, 4, 5, 6, 7));
        map.put("population_polsby_popper", List.of(1, 2, 3, 4, 5, 6, 7));
        map.put("population_fatness", List.of(1, 2, 3, 4, 5, 6, 7));

        return map;
    }

    @GetMapping("/constrainedData")
    public Map<String, Object> getConstrainedData(String[] incumbents,
                                                  String compactnessType,
                                                  double compactnessVal,
                                                  int mmDistricts,
                                                  String populationType,
                                                  double populationVal) {
//      int
        return null;
    }

    @GetMapping("/getConstrainedRankedDistrictings")
    public Map<String, Object> getConstrainedRankedDistrictings(String[] incumbents,
                                                                String compactnessType,
                                                                double compactnessVal,
                                                                int mmDistricts,
                                                                String populationType,
                                                                double populationVal,
                                                                double populationEquality,
                                                                double splitCounty,
                                                                double deviationFromEnacted,
                                                                double deviationFromAverage,
                                                                double compactness,
                                                                double populationFairness) {
//      Collection<DistrictingsSummary>
        return null;
    }

}
