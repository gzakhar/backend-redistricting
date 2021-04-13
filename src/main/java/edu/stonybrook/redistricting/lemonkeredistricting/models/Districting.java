package edu.stonybrook.redistricting.lemonkeredistricting.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Districting {

    @Id
    private Long id;
    private String geojson;


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

    @Override
    public String toString() {
        return "Districting{" +
                "id=" + id +
                ", geojson='" + geojson + '\'' +
                '}';
    }
}
