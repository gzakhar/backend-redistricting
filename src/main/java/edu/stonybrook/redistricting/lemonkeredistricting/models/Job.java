package edu.stonybrook.redistricting.lemonkeredistricting.models;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Job {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private Long state_id;
    private String seed_file;
    private Integer cooling_period;
    private Integer number_rounds;
    @OneToMany(mappedBy = "job_id")
    private Collection<Districting> districtings;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getState_id() {
        return state_id;
    }

    public void setState_id(Long state_id) {
        this.state_id = state_id;
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
                "id=" + id +
                ", state_id=" + state_id +
                ", seed_file='" + seed_file + '\'' +
                ", cooling_period=" + cooling_period +
                ", number_rounds=" + number_rounds +
                ", districtings=" + districtings +
                '}';
    }
}
