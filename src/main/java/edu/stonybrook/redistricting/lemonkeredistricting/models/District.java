package edu.stonybrook.redistricting.lemonkeredistricting.models;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class District {

    @Id
    @Column(name = "district_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long districtId;
    @Column(name = "districting_id")
    private Long districtingId;
    @Transient
    private Integer totalPopulation;
    @OneToMany
    @JoinTable(
            name = "district_precinct_map",
            joinColumns = @JoinColumn(name = "district_id"),
            inverseJoinColumns = @JoinColumn(name = "precinct_id")
    )
    private Collection<Precinct> precincts;

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public Long getDistrictingId() {
        return districtingId;
    }

    public void setDistrictingId(Long districtingId) {
        this.districtingId = districtingId;
    }

    public Collection<Precinct> getPrecincts() {
        return precincts;
    }

    public void setPrecincts(Collection<Precinct> precincts) {
        this.precincts = precincts;
    }

    @PostLoad
    public void setTotalPopulation(){

        Integer totalPopulation = 0;

        for(Precinct p: precincts){
            totalPopulation += p.getTotalPopulation();
        }

        this.totalPopulation = totalPopulation;
    }

    public Integer getTotalPopulation() {
        return totalPopulation;
    }

    @Override
    public String toString() {
        return "District{" +
                "districtId=" + districtId +
                ", precincts=" + precincts +
                '}';
    }
}
