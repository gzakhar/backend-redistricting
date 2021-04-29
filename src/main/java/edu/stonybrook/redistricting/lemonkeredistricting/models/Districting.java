package edu.stonybrook.redistricting.lemonkeredistricting.models;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Districting {

    private Long districtingId;
    private Collection<District> districts;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "districting_id")
    public Long getDistrictingId() {
        return districtingId;
    }

    public void setDistrictingId(Long districtingId) {
        this.districtingId = districtingId;
    }

    @OneToMany(mappedBy = "districtingId")
    public Collection<District> getDistricts() {
        return districts;
    }

    public void setDistricts(Collection<District> districts) {
        this.districts = districts;
    }

    @Override
    public String toString() {
        return "Districting{" +
                "districtingId=" + districtingId +
                ", districts=" + districts +
                '}';
    }
}
