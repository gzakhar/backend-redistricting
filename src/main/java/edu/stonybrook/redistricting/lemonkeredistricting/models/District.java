package edu.stonybrook.redistricting.lemonkeredistricting.models;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Entity
public class District {

    private Long districtId;
    private Long districtingId;
    private Integer totalPopulation;
    private Map<Ethnicity, Boolean> isMajorityMinority;
    private Collection<Precinct> precincts;

    @PostLoad
    private void setUp() {
        this.setTotalPopulation();
        this.setMajorityMinority();
    }

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

    @Transient
    public Integer getTotalPopulation() {
        return totalPopulation;
    }

    public void setTotalPopulation() {

        Integer totalPopulation = 0;

        for (Precinct p : precincts) {
            totalPopulation += p.getTotalPopulation();
        }

        this.totalPopulation = totalPopulation;
    }

    @Transient
    public Map<Ethnicity, Boolean> getMajorityMinority() {
        return isMajorityMinority;
    }

    public Boolean isMajorityMinority(Ethnicity ethnicity){
        return this.isMajorityMinority.get(ethnicity);
    }

    public void setMajorityMinority() {

        int totalPoulation = getTotalPopulation();

        this.isMajorityMinority = new HashMap<>();

        for (Ethnicity e : Ethnicity.values()) {

            int ethnisityPopulation = getEthnicityPopulation(e);

            double percentage = (double) ethnisityPopulation / (double) totalPoulation;
            System.out.println(percentage);

            this.isMajorityMinority.put(e, percentage >= 0.5);
        }

    }

    public Integer getEthnicityPopulation(Ethnicity ethnicity) {

        int ethnicityPopulation = 0;
        for (Precinct p : this.precincts) {
            ethnicityPopulation += p.getTotalEthnisityPopulation(ethnicity);
        }

        System.out.println(ethnicityPopulation);
        return ethnicityPopulation;
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
