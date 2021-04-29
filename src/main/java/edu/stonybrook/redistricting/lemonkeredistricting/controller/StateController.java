package edu.stonybrook.redistricting.lemonkeredistricting.controller;

import edu.stonybrook.redistricting.lemonkeredistricting.models.*;
import edu.stonybrook.redistricting.lemonkeredistricting.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lemonke")
public class StateController {

    @Autowired
    private DistrictingRepository districtingRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobSummaryRepository jobSummaryRepository;

    @Autowired
    private PrecinctRepository precinctRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private StateSummaryRepository stateSummaryRepository;

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

        return jobRepository.findAllByStateId(stateId);
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

        return jobSummaryRepository.findByStateId(stateId);
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

//    TODO: implement a maxMMDistricts for a districting (enacted)
//    @GetMapping("/states/{id}/max-mm-districts")
//    public List<StateSummary> getMaximumMMDistricts() {
//
//
//    }
}
