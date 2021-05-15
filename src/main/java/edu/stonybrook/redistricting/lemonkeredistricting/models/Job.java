package edu.stonybrook.redistricting.lemonkeredistricting.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.stonybrook.redistricting.lemonkeredistricting.service.ConstraintsBuilder;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "job_id")
    private Long jobId;
    @Column(name = "state_id")
    private Long stateId;
    @Column(name = "cooling_periods")
    private Integer coolingPrediods;
    @Column(name = "number_rounds")
    private Integer numberRounds;
    @Column(name = "number_runs")
    private Integer numberRuns;
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

    public Integer getCoolingPrediods() {
        return coolingPrediods;
    }

    public void setCoolingPrediods(Integer coolingPrediods) {
        this.coolingPrediods = coolingPrediods;
    }

    public Integer getNumberRounds() {
        return numberRounds;
    }

    public void setNumberRounds(Integer numberRounds) {
        this.numberRounds = numberRounds;
    }

    public Integer getNumberRuns() {
        return numberRuns;
    }

    public void setNumberRuns(Integer numberRuns) {
        this.numberRuns = numberRuns;
    }

    public Collection<Districting> getDistrictings() {
        return districtings;
    }

    public void setDistrictings(Collection<Districting> districtings) {
        this.districtings = districtings;
    }

    @Transient
    @JsonIgnore
    public Map<CompactnessType, Object> getCompactnessConstraintArray(){

        return ConstraintsBuilder.buildCompactnessConstraintsArray(jobId);
    }

    @Transient
    @JsonIgnore
    public Map<PopulationType, Object> getPopulationConstraintArray(){

        return ConstraintsBuilder.buildPopulationConstraintsArray(jobId);
    }

    @Override
    public String toString() {
        return "Job{" +
                "jobId=" + jobId +
                ", stateId=" + stateId +
                ", coolingPrediods=" + coolingPrediods +
                ", numberRounds=" + numberRounds +
                ", numberRounds=" + numberRounds +
                ", districtings=" + districtings +
                '}';
    }
}
