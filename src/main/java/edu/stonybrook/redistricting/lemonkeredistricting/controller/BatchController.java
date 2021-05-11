package edu.stonybrook.redistricting.lemonkeredistricting.controller;

import edu.stonybrook.redistricting.lemonkeredistricting.repo.PrecinctGeometryRepo;
import org.json.simple.JSONObject;
import org.locationtech.jts.geom.Geometry;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.Instant;

@RestController
@RequestMapping("/batch")
public class BatchController {

    @Resource
    private JobLauncher jobLauncher;
    @Resource
    private Job districtingSummaryGenerationJob;

    @Autowired
    private PrecinctGeometryRepo precinctGeometryRepo;

    @GetMapping("/run")
    public void run() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {

        jobLauncher.run(districtingSummaryGenerationJob,
                new JobParametersBuilder()
                        .addLong("timestamp", Instant.now().getEpochSecond())
                        .toJobParameters());
    }

    @GetMapping("/geometry/{precinctId}")
    public JSONObject testGeometryRepo(@PathVariable long precinctId){
        return precinctGeometryRepo.getPrecinctJson(precinctId);
    }


}
