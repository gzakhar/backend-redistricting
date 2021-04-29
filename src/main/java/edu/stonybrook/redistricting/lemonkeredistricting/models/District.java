package edu.stonybrook.redistricting.lemonkeredistricting.models;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class District {

    private Long districtId;
    private Long districtingId;
    private Integer totalPopulation;
    private Collection<Precinct> precincts;

    @Id
    @Column(name = "district_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    @Column(name = "districting_id")
    public Long getDistrictingId() {
        return districtingId;
    }

    public void setDistrictingId(Long districtingId) {
        this.districtingId = districtingId;
    }

    @PostLoad
    public void setTotalPopulation() {

        Integer totalPopulation = 0;

        for (Precinct p : precincts) {
            totalPopulation += p.getTotalPopulation();
        }

        this.totalPopulation = totalPopulation;
    }

    @Transient
    public Integer getTotalPopulation() {
        return totalPopulation;
    }

    @OneToMany
    @JoinTable(
            name = "district_precinct_map",
            joinColumns = @JoinColumn(name = "district_id"),
            inverseJoinColumns = @JoinColumn(name = "precinct_id")
    )
    public Collection<Precinct> getPrecincts() {
        return precincts;
    }

    public void setPrecincts(Collection<Precinct> precincts) {
        this.precincts = precincts;
    }

    @Override
    public String toString() {
        return "District{" +
                "districtId=" + districtId +
                ", precincts=" + precincts +
                '}';
    }
}
