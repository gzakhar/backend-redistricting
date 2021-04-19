package edu.stonybrook.redistricting.lemonkeredistricting.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Districting {

    @Id
    private Long id;
    private String geojson;
    private Long job_id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGeojson() {
        return geojson;
    }

    public void setGeojson(String geojson) {
        this.geojson = geojson;
    }

    public Long getJob_id() {
        return job_id;
    }

    public void setJob_id(Long job_id) {
        this.job_id = job_id;
    }

    @Override
    public String toString() {
        return "Districting{" +
                "id=" + id +
                ", geojson='" + geojson + '\'' +
                ", job_id=" + job_id +
                '}';
    }
}
