package edu.stonybrook.redistricting.lemonkeredistricting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String home(){
        return "Hello World!";
    }

    @GetMapping("/states")
    public List<Map<String, Object>> getState() {
        return jdbcTemplate.queryForList("SELECT * FROM states", new HashMap<>());
    }

    @GetMapping("/jobs")
    public List<Map<String, Object>> getJobs(){
        return jdbcTemplate.queryForList("SELECT * FROM jobs", new HashMap<>());
    }
}
