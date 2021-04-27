package edu.stonybrook.redistricting.lemonkeredistricting.controller;

import edu.stonybrook.redistricting.lemonkeredistricting.models.Districting;
import edu.stonybrook.redistricting.lemonkeredistricting.models.Incumbent;
import edu.stonybrook.redistricting.lemonkeredistricting.models.Job;
import edu.stonybrook.redistricting.lemonkeredistricting.models.State;
import edu.stonybrook.redistricting.lemonkeredistricting.repo.DistrictingRepository;
import edu.stonybrook.redistricting.lemonkeredistricting.repo.IncumbentRepository;
import edu.stonybrook.redistricting.lemonkeredistricting.repo.JobRepository;
import edu.stonybrook.redistricting.lemonkeredistricting.repo.StateRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@RequestMapping("/lemonke")
public class DistrictingController {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private DistrictingRepository districtingRepository;

    @Autowired
    private IncumbentRepository incumbentRepository;

    @GetMapping("/test")
    public String home() {
        return "Hello World!";
    }

    @GetMapping("/states")
    public List<State> getState() {

        return stateRepository.findAll();
    }

    @GetMapping("/states/{id}")
    public Optional<State> getStateById(HttpSession httpSession, @PathVariable long id) {
        httpSession.setAttribute("state", id);
        return stateRepository.findById(id);
    }

    @GetMapping("/states/{id}/jobs")
    public List<Job> getJobs(HttpSession httpSession, @PathVariable long id) {
        System.out.println(httpSession.getAttribute("state"));
        return jobRepository.findAllByStateId(id);
    }

    @GetMapping("/states/{stateId}/incumbents")
    public Collection<Incumbent> getIncumbents(@PathVariable long stateId) {

        Optional<State> state = stateRepository.findById(stateId);

        if (state.isPresent()) {
            return state.get().getIncumbents();
        }

        return null;
    }

    @ApiOperation(value = "Generate Link Token", notes = "Generate link token for standard flow and for 'Update mode' and 'Micro deposits verifications'")
    @GetMapping("/jobs/{id}")
    public Optional<Job> getJob(@ApiParam(value = "Optional parameter, used for 'Update mode' and 'Micro deposits verifications' flows") @PathVariable long id) {

        return jobRepository.findById(id);
    }

    @GetMapping("/districtings/{id}")
    public Optional<Districting> getDistrictingById(@PathVariable long id) {

        return districtingRepository.findById(id);
    }

    @GetMapping("/districtings")
    public List<Districting> getDistrictings() {

        return (List<Districting>) districtingRepository.findAll();
    }

    @GetMapping("/jobs/{id}/districtings")
    public List<Districting> getDistrictingsByJobId(@PathVariable Long id) {

        return districtingRepository.findAllByJobId(id);
    }

    @GetMapping("/getConstraintCountIncumbent")
    public List<Map<String, Object>> getConstraintCountIncumbent(int[] incumbents, int jobId) {
//      int
        return null;
    }

    @GetMapping("/getConstraintArrays")
    public Map<String, List<Integer>> getConstraintArrays(int jobId) {
        List<Map<String, List<Integer>>> list = new ArrayList<>();
        Map<String, List<Integer>> map = new HashMap<>();

        map.put("population_area", List.of(1, 2, 3, 4, 5, 6, 7));
        map.put("population_polsby_popper", List.of(1, 2, 3, 4, 5, 6, 7));
        map.put("population_fatness", List.of(1, 2, 3, 4, 5, 6, 7));

        return map;
    }

    @GetMapping("/getConstrainedData")
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


    @GetMapping("/getDistricting")
    public Map<String, Object> getDistricting(int districtingId) {
//        districting
        return null;
    }


    @PostMapping("/setConstraints")
    public String setConstraints(@RequestBody String body) {
        return body;
    }

    @PostMapping("/setMeasures")
    public String setMeasures(@RequestBody String body) {
        return body;
    }

}
