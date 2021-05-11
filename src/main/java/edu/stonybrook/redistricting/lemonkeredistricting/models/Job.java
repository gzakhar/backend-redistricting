package edu.stonybrook.redistricting.lemonkeredistricting.models;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Job {

    private Long jobId;
    private Long stateId;
    private String seed_file;
    private Integer cooling_periods;
    private Integer number_rounds;
    private Integer number_runs;
    private Collection<Districting> districtings;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "job_id")
    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    @Column(name = "state_id")
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

    public Integer getCooling_periods() {
        return cooling_periods;
    }

    public void setCooling_periods(Integer cooling_periods) {
        this.cooling_periods = cooling_periods;
    }

    public Integer getNumber_rounds() {
        return number_rounds;
    }

    public void setNumber_rounds(Integer number_rounds) {
        this.number_rounds = number_rounds;
    }

    public Integer getNumber_runs() {
        return number_runs;
    }

    public void setNumber_runs(Integer number_runs) {
        this.number_runs = number_runs;
    }

    @OneToMany
    @JoinTable(
            name = "job_districting_map",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "districting_id")
    )
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
                ", cooling_periods=" + cooling_periods +
                ", number_rounds=" + number_rounds +
                ", number_runs=" + number_runs +
                ", districtings=" + districtings +
                '}';
    }
}
