package edu.stonybrook.redistricting.lemonkeredistricting.models;

import javax.persistence.*;

@Entity
@Table(name = "job")
public class JobSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "job_id")
    private Long jobId;
    private String name;
    @Column(name = "cooling_periods")
    private Integer coolingPrediods;
    @Column(name = "number_rounds")
    private Integer numberRounds;
    @Column(name = "number_runs")
    private Integer numberRuns;
    @Column(name = "max_pop_diff_percentage")
    private Double maxPopDiffPercentage;
    @Column(name = "number_districtings")
    private Integer numberDistrictings;

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumberDistrictings() {
        return numberDistrictings;
    }

    public void setNumberDistrictings(Integer numberDistrictings) {
        this.numberDistrictings = numberDistrictings;
    }

    public Integer getCoolingPrediods() {
        return coolingPrediods;
    }

    public void setCoolingPrediods(Integer coolingPrediods) {
        this.coolingPrediods = coolingPrediods;
    }

    public Integer getNumberRuns() {
        return numberRuns;
    }

    public void setNumberRuns(Integer numberRuns) {
        this.numberRuns = numberRuns;
    }

    public Double getMaxPopDiffPercentage() {
        return maxPopDiffPercentage;
    }

    public void setMaxPopDiffPercentage(Double maxPopDiffPercentage) {
        this.maxPopDiffPercentage = maxPopDiffPercentage;
    }

    public Integer getNumberRounds() {
        return numberRounds;
    }

    public void setNumberRounds(Integer numberRounds) {
        this.numberRounds = numberRounds;
    }

    @Override
    public String toString() {
        return "JobSummary{" +
                "jobId=" + jobId +
                ", name='" + name + '\'' +
                ", coolingPrediods=" + coolingPrediods +
                ", numberRounds=" + numberRounds +
                ", numberRuns=" + numberRuns +
                ", maxPopDiffPercentage=" + maxPopDiffPercentage +
                ", numberDistrictings=" + numberDistrictings +
                '}';
    }
}
