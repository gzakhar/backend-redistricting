package edu.stonybrook.redistricting.lemonkeredistricting.models;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Districting {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "districting_id")
    private Long districtingId;
    @OneToMany(mappedBy = "districtingId")
    private Collection<District> districts;

    public Long getDistrictingId() {
        return districtingId;
    }

    public void setDistrictingId(Long districtingId) {
        this.districtingId = districtingId;
    }

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
