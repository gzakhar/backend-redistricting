package edu.stonybrook.redistricting.lemonkeredistricting.models;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "job_id")
    private Long jobId;
    @Column(name = "state_id")
    private Long stateId;
    private String seed_file;
    private Integer cooling_period;
    private Integer number_rounds;
    @OneToMany
    @JoinTable(
            name = "job_districting_map",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "districting_id")
    )
    private Collection<Districting> districtings;

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    public String getSeed_file() {
        return seed_file;
    }

    public void setSeed_file(String seed_file) {
        this.seed_file = seed_file;
    }

    public Integer getCooling_period() {
        return cooling_period;
    }

    public void setCooling_period(Integer cooling_period) {
        this.cooling_period = cooling_period;
    }

    public Integer getNumber_rounds() {
        return number_rounds;
    }

    public void setNumber_rounds(Integer number_rounds) {
        this.number_rounds = number_rounds;
    }

    public Collection<Districting> getDistrictings() {
        return districtings;
    }

    public void setDistrictings(Collection<Districting> districtings) {
        this.districtings = districtings;
    }

    @Override
    public String toString() {
        return "Job{" +
                "jobId=" + jobId +
                ", stateId=" + stateId +
                ", seed_file='" + seed_file + '\'' +
                ", cooling_period=" + cooling_period +
                ", number_rounds=" + number_rounds +
                ", districtings=" + districtings +
                '}';
    }
}
