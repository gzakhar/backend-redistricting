package edu.stonybrook.redistricting.lemonkeredistricting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/lemonke")
public class DistrictingController {

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }


    @GetMapping("/test")
    public String home() {
        return "Hello World!";
    }

    @GetMapping("/states")
    public List<Map<String, Object>> getState() {
        return jdbcTemplate.queryForList("SELECT * FROM states", new HashMap<>());
    }

    @GetMapping("/enacted")
    public List<Map<String, Object>> getEnacted() {
        return jdbcTemplate.queryForList("select d.id, d.geojson from states s\n" +
                "inner join districtings d on s.ENACTED_DISTRICTING_ID = d.id", new HashMap<>());
    }

    @GetMapping("/jobs")
    public List<Map<String, Object>> getJobs() {
        return jdbcTemplate.queryForList("SELECT * FROM jobs", new HashMap<>());
    }

    @GetMapping("/incumbents/{stateId}")
    public List<Map<String, Object>> getIncumbents(@PathVariable int stateId) {

        Map<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(stateId));

        return jdbcTemplate.queryForList("select * from incumbents i\n" +
                "inner join states s on i.state_id = s.id \n" +
                "where s.id = (:id)", params);
    }

    @GetMapping("/getConstraintCountIncumbent")
    public List<Map<String, Object>> getConstraintCountIncumbent(int[] incumbents, int jobId) {
//      int
        return null;
    }

    @GetMapping("/getConstraintArrays")
    public List<Map<String, int[]>> getConstraintArrays(int jobId) {
//      int
        return null;
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
