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

    @Transient
    @JsonIgnore
    private Geometry geometry = null;

    /**
     * Getters Setters
     */
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

    /**
     * Calculated Methods
     */
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

//        long start = System.currentTimeMillis();
        Integer ret = this.precincts.stream()
                .map(p -> p.getTotalPopulationType(populationType))
                .filter(Objects::nonNull)
                .reduce(0, Integer::sum);

//        System.out.println("time: " + (System.currentTimeMillis() - start));
        return ret;
    }

    @Transient
    @JsonIgnore
    public List<Long> getPrecinctIds() {

        return precincts.stream().map(Precinct::getPrecinctId).collect(Collectors.toList());
    }

    @Transient
    @JsonIgnore
    public List<Precinct> getPrecinctList() {

        return new ArrayList<>(precincts);
    }

    @Transient
    @JsonIgnore
    public Geometry getGeometry() {

        if (geometry == null) {
            this.geometry = GeometryCalculation.calculateDistrictGeometry(this.getPrecinctIds());
        }
        return geometry;
    }

    @Transient
    @JsonIgnore
    public Double getPerimeter() {

        return getGeometry().getLength();
    }

    @Transient
    @JsonIgnore
    public Double getArea() {

        return getGeometry().getArea();
    }

    @Transient
    @JsonIgnore
    public Integer getNumberPrecincts() {

        return precincts.size();
    }

    @Override
    public String toString() {
        return "District{" +
                "districtId=" + districtId +
                ", precincts=" + precincts +
                '}';
    }
}
