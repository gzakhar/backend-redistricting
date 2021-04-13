package edu.stonybrook.redistricting.lemonkeredistricting.controller;

import edu.stonybrook.redistricting.lemonkeredistricting.models.Districting;
import edu.stonybrook.redistricting.lemonkeredistricting.models.Job;
import edu.stonybrook.redistricting.lemonkeredistricting.models.State;
import edu.stonybrook.redistricting.lemonkeredistricting.repo.DistrictingRepository;
import edu.stonybrook.redistricting.lemonkeredistricting.repo.JobRepository;
import edu.stonybrook.redistricting.lemonkeredistricting.repo.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
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

    @GetMapping("/test")
    public String home() {
        return "Hello World!";
    }

    @GetMapping("/states")
    public List<State> getState() {

        return stateRepository.findAll();
    }

    @GetMapping("/states/{id}")
    public Optional<State> getStateById(@PathVariable long id) {

        return stateRepository.findById(id);
    }



    @GetMapping("/states/{id}/jobs")
    public List<Job> getJobs(@PathVariable long id) {

        return jobRepository.findAllByStateId(id);
    }


    @GetMapping("/districtings/{id}")
    public Optional<Districting> getDistrictingById(@PathVariable long id) {

        return districtingRepository.findById(id);
    }

    @GetMapping("/districtings")
    public List<Districting> getDistrictings() {

        return (List<Districting>)districtingRepository.findAll();
    }

    @GetMapping("/jobs/{id}/districtings")
    public List<Districting> getDistrictingsByJobId(@PathVariable Long id) {

        return districtingRepository.findAllByJobId(id);
    }



    @GetMapping("/incumbents/{stateId}")
    public List<Map<String, Object>> getIncumbents(@PathVariable int stateId) {

        Map<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(stateId));

        return null;

//        return jdbcTemplate.queryForList("select * from incumbents i\n" +
//                "inner join states s on i.state_id = s.id \n" +
//                "where s.id = (:id)", params);
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

}
