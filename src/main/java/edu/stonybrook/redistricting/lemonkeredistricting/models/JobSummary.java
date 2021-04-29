package edu.stonybrook.redistricting.lemonkeredistricting.models;

import javax.persistence.*;

@Entity
@Table(name = "job")
public class JobSummary {

    private Long jobId;
    private String name;
    private Integer numberDistrictings;
    private Integer coolingPeriod;
    private Integer numberRounds;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "job_id")
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

    @Column(name = "number_districtings")
    public Integer getNumberDistrictings() {
        return numberDistrictings;
    }

    public void setNumberDistrictings(Integer numberDistrictings) {
        this.numberDistrictings = numberDistrictings;
    }

    @Column(name = "cooling_period")
    public Integer getCoolingPeriod() {
        return coolingPeriod;
    }

    public void setCoolingPeriod(Integer coolingPeriod) {
        this.coolingPeriod = coolingPeriod;
    }

    @Column(name = "number_rounds")
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
                ", numberDistrictings=" + numberDistrictings +
                ", coolingPeriod=" + coolingPeriod +
                ", numberRounds=" + numberRounds +
                '}';
    }
}
