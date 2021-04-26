package edu.stonybrook.redistricting.lemonkeredistricting.models;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "state")
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long stateId;
    private String name;
    private Long enacted_districting_id;
    private Double longitude;
    private Double latitude;
    private Double zoom;
    @OneToMany(mappedBy = "stateId")
    private Collection<Incumbent> incumbents;

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
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

    public Collection<Incumbent> getIncumbents() {
        return incumbents;
    }

    public void setIncumbents(Collection<Incumbent> incumbents) {
        this.incumbents = incumbents;
    }

    @Override
    public String toString() {
        return "State{" +
                "stateId=" + stateId +
                ", name='" + name + '\'' +
                ", enacted_districting_id=" + enacted_districting_id +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", zoom=" + zoom +
                ", incumbents=" + incumbents +
                '}';
    }
}
