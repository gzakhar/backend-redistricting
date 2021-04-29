package edu.stonybrook.redistricting.lemonkeredistricting.controller;

import edu.stonybrook.redistricting.lemonkeredistricting.models.*;
import edu.stonybrook.redistricting.lemonkeredistricting.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lemonke")
public class StateController {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private PrecinctRepository precinctRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private StateSummaryRepository stateSummaryRepository;

    @Autowired
    private JobSummaryRepository jobSummaryRepository;

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

    @GetMapping("/states/{stateId}/incumbents")
    public Collection<Incumbent> getIncumbents(@PathVariable long stateId) {

        Optional<State> state = stateRepository.findById(stateId);

        return state.map(State::getIncumbents).orElse(null);
    }

    @GetMapping("/states/{id}/precincts")
    public List<Precinct> getPrecincts(@PathVariable Long id) {

        return precinctRepository.getPrecinctByStateId(id);
    }


//      Im testing a way to retrieve enacted geojsons in realtime
//    @GetMapping("/states/{id}/precincts")
//    @Cacheable("precincts")
//    public List<Precinct> getPrecincts(@PathVariable Long id) {
//        var start = System.currentTimeMillis();
//        var result = precinctRepository.getPrecinctByStateId(id);
//        var end = System.currentTimeMillis();
//
//        System.out.println("*** execution time: " + (end - start));
//
//        return result;
//    }


    @GetMapping("/states/{id}/job-summaries")
    public List<JobSummary> getJobSummariesByState(@PathVariable Long id) {
        return jobSummaryRepository.findByStateId(id);
    }

    @GetMapping("/state-summaries")
    public List<StateSummary> getStateSummaries() {
        return stateSummaryRepository.findAll();
    }

}
