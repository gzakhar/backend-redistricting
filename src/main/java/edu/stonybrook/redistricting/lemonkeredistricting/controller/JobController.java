package edu.stonybrook.redistricting.lemonkeredistricting.controller;

import edu.stonybrook.redistricting.lemonkeredistricting.models.*;
import edu.stonybrook.redistricting.lemonkeredistricting.repo.DistrictStatRepository;
import edu.stonybrook.redistricting.lemonkeredistricting.repo.DistrictingRepository;
import edu.stonybrook.redistricting.lemonkeredistricting.repo.DistrictingSummaryRepository;
import edu.stonybrook.redistricting.lemonkeredistricting.repo.WulfJobRepository;
import edu.stonybrook.redistricting.lemonkeredistricting.service.BoxWhiskerService;
import edu.stonybrook.redistricting.lemonkeredistricting.service.ConstraintsBuilder;
import edu.stonybrook.redistricting.lemonkeredistricting.service.ObjectiveFunctionCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/lemonke")
public class JobController {

    @Autowired
    private DistrictingRepository districtingRepository;

    @Autowired
    private WulfJobRepository WulfJobRepository;

    @Autowired
    private DistrictingSummaryRepository districtingSummaryRepository;

    @Autowired
    private ConstraintsBuilder constraintsBuilder;

    @Autowired
    private ObjectiveFunctionCalculator objectiveFunctionCalculator;

    @Autowired
    private BoxWhiskerService boxWhiskerService;

    @GetMapping("/jobs")
    public List<Job> getAllJobs() {

        return WulfJobRepository.findAll();
    }

    @GetMapping("/jobs/{jobId}")
    public Optional<Job> getJobById(@PathVariable Long jobId) {

        return WulfJobRepository.findById(jobId);
    }

    @GetMapping("/jobs/{jobId}/districtings")
    public List<Districting> getDistrictingsByJobId(@PathVariable Long jobId) {

        return districtingRepository.findAllByJobId(jobId);
    }

    @GetMapping("/jobs/{jobId}/set")
    public void setCurrentJob(HttpSession httpSession, @PathVariable Long jobId) {

        httpSession.setAttribute("currentJob", WulfJobRepository.findById(jobId));
    }

    @PostMapping("/jobs/{jobId}")
    public String setJob(HttpSession httpSession, @PathVariable Long jobId) {

        Optional<Job> job = WulfJobRepository.findById(jobId);
        if (job.isPresent()) {
            httpSession.setAttribute("current-job", job);
            return "Succeessful setAttribute jobId: " + jobId;
        }

        throw new IllegalArgumentException("Error setAttribute jobId: " + jobId);
    }

    @GetMapping("/jobs/{jobId}/compactness-constraints")
    public Map<CompactnessType, Object> getCompactnessConstraints(@PathVariable Long jobId) {

        return constraintsBuilder.buildCompactnessConstraintsArray(jobId);
    }

    @GetMapping("/jobs/{jobId}/population-constraints")
    public Map<PopulationType, Object> getPopulationConstraints(@PathVariable Long jobId) {

        return constraintsBuilder.buildPopulationConstraintsArray(jobId);
    }

    @GetMapping("/jobs/{jobId}/districting-summaries")
    public List<DistrictingSummary> getDistrictingSummaries(@PathVariable Long jobId) {

        return districtingSummaryRepository.findDistrictingSummaryByJobId(jobId);
    }

    @GetMapping("/jobs/{jobId}/constrain-job")
    public List<DistrictingSummary> getConstrainedDistrictingSummaries(@PathVariable Long jobId,
                                                                       @RequestParam CompactnessType compactnessType,
                                                                       @RequestParam Double compactnessValue,
                                                                       @RequestParam Integer mmDistricts,
                                                                       @RequestParam Ethnicity ethnicity,
                                                                       @RequestParam Double threshold,
                                                                       @RequestParam PopulationType populationType,
                                                                       @RequestParam Double populationValue) {

        return constraintsBuilder.constrainJob(jobId,
                null,
                compactnessType,
                compactnessValue,
                mmDistricts,
                ethnicity,
                threshold,
                populationType,
                populationValue);
    }




//    @GetMapping("")
//    public List<DistrictingScore> getObjScores(){
//
////        return objectiveFunctionCalculator.calculateObjectiveFunction();
//        return null;
//    }
}
