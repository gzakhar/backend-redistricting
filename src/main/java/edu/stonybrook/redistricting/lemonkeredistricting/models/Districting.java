package edu.stonybrook.redistricting.lemonkeredistricting.models;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Districting {

    private Long districtingId;
    private Collection<District> districts;
    private Map<Ethnicity, Integer> mMDistrictCount;

    @PostLoad
    private void setUp() {
        this.setMMDistrictCount();
    }

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

    private void setMMDistrictCount() {

        this.mMDistrictCount = new HashMap<>();

        for (District d : districts)
            for (Ethnicity e : Ethnicity.values())
                if (d.isMajorityMinority(e))
                    this.mMDistrictCount.put(e, this.mMDistrictCount.getOrDefault(e, 0) + 1);

    }

    @Transient
    public Map<Ethnicity, Integer> getMMDistrictCount() {
        return this.mMDistrictCount;
    }


    @Override
    public String toString() {
        return "Districting{" +
                "districtingId=" + districtingId +
                ", districts=" + districts +
                '}';
    }
}
