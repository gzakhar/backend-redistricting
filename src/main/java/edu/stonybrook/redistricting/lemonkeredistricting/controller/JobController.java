package edu.stonybrook.redistricting.lemonkeredistricting.controller;

import edu.stonybrook.redistricting.lemonkeredistricting.models.Districting;
import edu.stonybrook.redistricting.lemonkeredistricting.models.Job;
import edu.stonybrook.redistricting.lemonkeredistricting.repo.DistrictingRepository;
import edu.stonybrook.redistricting.lemonkeredistricting.repo.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lemonke")
public class JobController {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private DistrictingRepository districtingRepository;


    @GetMapping("/jobs")
    public List<Job> getAllJobs() {

        return jobRepository.findAll();
    }

    @GetMapping("/jobs/{jobId}")
    public Optional<Job> getJobById(@PathVariable Long jobId) {

        return jobRepository.findById(jobId);
    }

    @GetMapping("/jobs/{jobId}/districtings")
    public List<Districting> getDistrictingsByJobId(@PathVariable Long jobId) {

        return districtingRepository.findAllByJobId(jobId);
    }

    @GetMapping("/jobs/{jobId}/set")
    public void setCurrentJob(HttpSession httpSession, @PathVariable Long jobId) {

        httpSession.setAttribute("currentJob", jobRepository.findById(jobId));
    }
}
