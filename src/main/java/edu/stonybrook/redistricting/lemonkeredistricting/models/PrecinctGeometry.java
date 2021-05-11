package edu.stonybrook.redistricting.lemonkeredistricting.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "precinct")
public class PrecinctGeometry {

    @Id
    @Column(name = "precinct_id")
    private long precinctId;

    @Column(name = "geometry")
    private String geometry;

    public long getPrecinctId() {
        return precinctId;
    }

    public void setPrecinctId(long precinctId) {
        this.precinctId = precinctId;
    }

    public String getGeometry() {
        return geometry;
    }

    public void setGeometry(String geometry) {
        this.geometry = geometry;
    }

    @Override
    public String toString() {
        return "PrecinctGeometry{" +
                "precinctId=" + precinctId +
                ", geometry='" + geometry + '\'' +
                '}';
    }
}
