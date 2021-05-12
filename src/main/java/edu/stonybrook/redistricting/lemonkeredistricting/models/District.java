package edu.stonybrook.redistricting.lemonkeredistricting.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.stonybrook.redistricting.lemonkeredistricting.service.GeometryCalculation;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryCollection;
import org.locationtech.jts.geom.GeometryFactory;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class District {

    @Id
    @Column(name = "district_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long districtId;

    @Column(name = "districting_id")
    private Long districtingId;

    @OneToMany
    @JoinTable(
            name = "district_precinct_map",
            joinColumns = @JoinColumn(name = "district_id"),
            inverseJoinColumns = @JoinColumn(name = "precinct_id")
    )
    private Collection<Precinct> precincts;

    /** Getters Setters */
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

    /** Calculated Methods */
    @Transient
    @JsonIgnore
    public Map<Ethnicity, Boolean> isMajorityMinority() {

        Map<Ethnicity, Boolean> isMajorityMinority = new HashMap<>();

        for (Ethnicity e : Ethnicity.values()) {
            isMajorityMinority.put(e, isMajorityMinority(e));
        }

        return isMajorityMinority;
    }

    @Transient
    @JsonIgnore
    public Boolean isMajorityMinority(Ethnicity ethnicity) {

        Integer ethnicityPopulation = getPopulation(ethnicity, PopulationType.TOTAL_POPULATION);
        Integer totalPopulation = getTotalPopulation(PopulationType.TOTAL_POPULATION);

//        TODO: No 0.5
        return ((double) ethnicityPopulation / (double) totalPopulation) >= 0.5;
    }

    @Transient
    @JsonIgnore
    public Integer getPopulation(Ethnicity ethnicity, PopulationType populationType) {

        return this.precincts.stream()
                .map(p -> p.getPopulation(ethnicity, populationType))
                .reduce(0, Integer::sum);
    }

    @Transient
    @JsonIgnore
    public Integer getTotalPopulation(PopulationType populationType) {

        return this.precincts.stream()
                .map(p -> p.getTotalPopulationType(populationType))
                .reduce(0, Integer::sum);
    }

    @Transient
    @JsonIgnore
    public Integer[] getPrecinctIds() {

        return precincts.stream().map(precinct -> precinct.getPrecinctId().intValue()).toArray(Integer[]::new);
    }

    @Transient
    @JsonIgnore
    public Geometry getGeometry() {

        Geometry[] geometryArray = new Geometry[precincts.size()];

        int i = 0;
        for (Precinct p : precincts) {
            geometryArray[i++] = p.getGeometry();
        }

        GeometryCollection geometryCollection = new GeometryCollection(geometryArray, new GeometryFactory());

        return geometryCollection.union().getBoundary();
    }


    @Override
    public String toString() {
        return "District{" +
                "districtId=" + districtId +
                ", precincts=" + precincts +
                '}';
    }
}
