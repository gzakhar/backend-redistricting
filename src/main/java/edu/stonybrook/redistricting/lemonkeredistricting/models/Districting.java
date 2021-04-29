package edu.stonybrook.redistricting.lemonkeredistricting.models;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Districting {

    private Long districtingId;
    private Collection<District> districts;
//    private Map<Ethnicity, Integer> mMDistrictCount;

//    TODO: @PostLoad to initialize attributes.
//    @PostLoad
//    private void setUp() {
//        this.setMMDistrictCount();
//    }

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

    public Integer getMMDistrictCount(Ethnicity ethnicity) {

        return (int) districts.stream().map(d -> d.isMajorityMinority(ethnicity)).filter(d -> d).count();
    }

    @Transient
    public Integer getPopulation(Ethnicity ethnicity, PopulationType populationType) {

        return districts.stream().map(d -> d.getPopulation(ethnicity, populationType)).reduce(0, Integer::sum);
    }

    @Transient
    public Integer getTotalPopulation(PopulationType populationType) {

        return districts.stream().map(d -> d.getTotalPopulation(populationType)).reduce(0, Integer::sum);
    }

    @Transient
    public Integer getDistrictingCount() {

        return districts.size();
    }

    @Transient
    public Integer getMaxMMDistricts(Ethnicity ethnicity) {

        int ethnicityPopulation = getPopulation(ethnicity, PopulationType.TOTAL_POPULATION);
        int totalPopulation = getTotalPopulation(PopulationType.TOTAL_POPULATION);

        return (int) (((double) ethnicityPopulation / (double) totalPopulation) * (double) getDistrictingCount());
    }

    @Transient
    public Map<PopulationType, Boolean> getPopulationTypeAvailablability() {

        District district = districts.iterator().next();
        Precinct precinct = district.getPrecincts().iterator().next();

        Map<PopulationType, Boolean> populationAvailability = new HashMap<>();
        Arrays.stream(PopulationType.values()).forEach(p -> populationAvailability.put(p, precinct.isPopulationTypeAvailable(p)));

        return populationAvailability;
    }

    @Override
    public String toString() {
        return "Districting{" +
                "districtingId=" + districtingId +
                ", districts=" + districts +
                '}';
    }
}
