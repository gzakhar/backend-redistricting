package edu.stonybrook.redistricting.lemonkeredistricting.models;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Entity
public class District {

    private Long districtId;
    private Long districtingId;
    private Collection<Precinct> precincts;

    /* calculatable attributes*/
    private Map<Ethnicity, Integer> ethnicityPopulationMap = new HashMap<>();

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

    public Boolean isMajorityMinority(Ethnicity ethnicity) {

        Integer ethnicityPopulation = getPopulation(ethnicity, PopulationType.TOTAL_POPULATION);
        Integer totalPopulation = getTotalPopulation(PopulationType.TOTAL_POPULATION);

        return ((double) ethnicityPopulation / (double) totalPopulation) >= 0.5;
    }

    public Integer getPopulation(Ethnicity ethnicity, PopulationType populationType) {

        return this.precincts.stream()
                .map(p -> p.getPopulation(ethnicity, populationType))
                .reduce(0, Integer::sum);
    }

    public Integer getTotalPopulation(PopulationType populationType) {

        return this.precincts.stream()
                .map(p -> p.getTotalPopulationType(populationType))
                .reduce(0, Integer::sum);
    }


    @Override
    public String toString() {
        return "District{" +
                "districtId=" + districtId +
                ", precincts=" + precincts +
                '}';
    }
}
