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
public class StateController {

    @Autowired
    private WulfJobRepository WulfJobRepository;

    @Autowired
    private WulfJobSummaryRepository WulfJobSummaryRepository;

    @Autowired
    private PrecinctRepository precinctRepository;

    @Autowired
    private GeometryRepository geometryRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private StateSummaryRepository stateSummaryRepository;

    @Autowired
    private GeometryCalculation geometryCalculation;

    @GetMapping("/states")
    public List<State> getState() {

        return stateRepository.findAll();
    }

    @GetMapping("/states/{stateId}")
    public Optional<State> getStateById(@PathVariable Long stateId) {

        return stateRepository.findById(stateId);
    }

    @GetMapping("/states/{stateId}/jobs")
    public List<Job> getJobs(@PathVariable Long stateId) {

        return WulfJobRepository.findAllByStateId(stateId);
    }

    @GetMapping("/states/{stateId}/incumbents")
    public Collection<Incumbent> getIncumbents(@PathVariable Long stateId) {

        Optional<State> state = stateRepository.findById(stateId);

        return state.map(State::getIncumbents).orElse(null);
    }

    @GetMapping("/states/{stateId}/precincts")
    public List<Precinct> getPrecincts(@PathVariable Long stateId) {

        return precinctRepository.getPrecinctByStateId(stateId);
    }

//      Im testing a way to retrieve enacted geojsons in realtime
//    @GetMapping("/states/{id}/precincts")
//    @Cacheable("precincts")
//    public List<Precinct> getPrecincts(@PathVariable Long id) {
//
//        var start = System.currentTimeMillis();
//        var result = precinctRepository.getPrecinctByStateId(id);
//        var end = System.currentTimeMillis();
//
//        System.out.println("*** execution time: " + (end - start));
//
//        return result;
//    }

    @GetMapping("/states/{stateId}/job-summaries")
    public List<JobSummary> getJobSummariesByState(@PathVariable Long stateId) {

        return WulfJobSummaryRepository.findByStateId(stateId);
    }

    @GetMapping("/states/state-summaries")
    public List<StateSummary> getStateSummaries() {

        return stateSummaryRepository.findAll();
    }

    @GetMapping("/states/{stateId}/available-ethnicities")
    public List<Ethnicity> getAvailableEthnicities(@PathVariable Long stateId) {

        return Arrays.asList(Ethnicity.values());
    }

    @PostMapping("/states/{stateId}")
    public String setState(HttpSession httpSession, @PathVariable Long stateId) {

        Optional<State> state = stateRepository.findById(stateId);
        if (state.isPresent()) {
            httpSession.setAttribute("current-state", state);
            return "Succeessful setAttribute stateId: " + stateId;
        }

        throw new IllegalArgumentException("Error setAttribute stateId: " + stateId);
    }

    @GetMapping("/states/{stateId}/precinct-geometries")
    public JSONObject getPrecinctGeometries(@PathVariable Long stateId) throws IOException, ParseException {

        return geometryCalculation.calculatePrecinctGeometry(stateId);
    }

    @GetMapping("/states/{stateId}/precinct-json")
    public JSONObject getPrecinctJson(@PathVariable Long stateId){

        JSONObject precinctsJson = new JSONObject();

        for(PrecinctGeometry precinctGeometry: geometryRepository.findAllByStateId(stateId)){
            precinctsJson.put(precinctGeometry.getPrecinctId(), precinctGeometry.getGeometry());
        }

        return precinctsJson;
    }

    @GetMapping("/states/{stateId}/geometry-union")
    public JSONObject getGeometryUnion(@PathVariable Long stateId){
        return StateMemoryRepository.getStateGeometry(stateId);
    }

//    TODO: implement a maxMMDistricts for a districting (enacted)
//    @GetMapping("/states/{id}/max-mm-districts")
//    public List<StateSummary> getMaximumMMDistricts() {
//    }
}
