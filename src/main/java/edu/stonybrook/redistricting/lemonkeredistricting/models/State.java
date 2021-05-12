package edu.stonybrook.redistricting.lemonkeredistricting.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.json.simple.JSONObject;
import org.locationtech.jts.geom.Geometry;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "state")
public class State {

    private Long stateId;
    private String name;
    private Long enacted_districting_id;
    private Double longitude;
    private Double latitude;
    private Double zoom;
    private Collection<Incumbent> incumbents;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "state_id")
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

    @OneToMany(mappedBy = "stateId")
    public Collection<Incumbent> getIncumbents() {
        return incumbents;
    }

    public void setIncumbents(Collection<Incumbent> incumbents) {
        this.incumbents = incumbents;
    }



//    @Transient
//    @OneToMany(mappedBy = "stateId")
//    public PrecinctGeometry getPrecinctGeometries(){
//
//    }

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
