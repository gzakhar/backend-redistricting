package edu.stonybrook.redistricting.lemonkeredistricting.models;

import javax.persistence.*;

//@Table(name="state")
@Entity
public class State {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private String name;
    private Long enacted_districting_id;
    private Double longitude;
    private Double latitude;
    private Double zoom;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getEnacted_districting_id() {
        return enacted_districting_id;
    }

    public void setEnacted_districting_id(Long enacted_districting_id) {
        this.enacted_districting_id = enacted_districting_id;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getZoom() {
        return zoom;
    }

    public void setZoom(Double zoom) {
        this.zoom = zoom;
    }

    @Override
    public String toString() {
        return "State{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", enacted_districting_id=" + enacted_districting_id +
                '}';
    }
}
