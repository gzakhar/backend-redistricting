package edu.stonybrook.redistricting.lemonkeredistricting.controller;

import edu.stonybrook.redistricting.lemonkeredistricting.models.Districting;
import edu.stonybrook.redistricting.lemonkeredistricting.models.Job;
import edu.stonybrook.redistricting.lemonkeredistricting.repo.DistrictingRepository;
import edu.stonybrook.redistricting.lemonkeredistricting.repo.SWJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lemonke")
public class JobController {

    @Autowired
    private DistrictingRepository districtingRepository;

    @Autowired
    private SWJobRepository SWJobRepository;

    @GetMapping("/jobs")
    public List<Job> getAllJobs() {

        return SWJobRepository.findAll();
    }

    @GetMapping("/jobs/{jobId}")
    public Optional<Job> getJobById(@PathVariable Long jobId) {

        return SWJobRepository.findById(jobId);
    }

    @GetMapping("/jobs/{jobId}/districtings")
    public List<Districting> getDistrictingsByJobId(@PathVariable Long jobId) {

        return districtingRepository.findAllByJobId(jobId);
    }

    @GetMapping("/jobs/{jobId}/set")
    public void setCurrentJob(HttpSession httpSession, @PathVariable Long jobId) {

        httpSession.setAttribute("currentJob", SWJobRepository.findById(jobId));
    }

    @PostMapping("/jobs/{jobId}")
    public String setJob(HttpSession httpSession, @PathVariable Long jobId) {

        Optional<Job> job = SWJobRepository.findById(jobId);
        if (job.isPresent()) {
            httpSession.setAttribute("current-job", job);
            return "Succeessful setAttribute jobId: " + jobId;
        }

        throw new IllegalArgumentException("Error setAttribute jobId: " + jobId);
    }
}
