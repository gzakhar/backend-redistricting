package edu.stonybrook.redistricting.lemonkeredistricting.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.stonybrook.redistricting.lemonkeredistricting.service.GeometryCalculation;
import edu.stonybrook.redistricting.lemonkeredistricting.service.GillConstruct;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import springfox.documentation.spring.web.json.Json;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class Districting {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "districting_id")
    private Long districtingId;

    @OneToMany(mappedBy = "districtingId")
    private Collection<District> districts;

    /**
     * Getters Setters.
     **/
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

    /**
     * Class Functions.
     **/
    @Transient
    @JsonIgnore
    public Integer getMMDistrictCount(Ethnicity ethnicity) {

        return (int) districts.stream().map(d -> d.isMajorityMinority(ethnicity)).filter(d -> d).count();
    }

    @Transient
    @JsonIgnore
    public Integer getPopulation(Ethnicity ethnicity, PopulationType populationType) {

        return districts.stream().map(d -> d.getPopulation(ethnicity, populationType)).reduce(0, Integer::sum);
    }

    @Transient
    @JsonIgnore
    public Integer getTotalPopulation(PopulationType populationType) {

        return districts.stream().map(d -> d.getTotalPopulation(populationType)).reduce(0, Integer::sum);
    }

    @Transient
    @JsonIgnore
    public Integer getNumberDistricts() {

        return districts.size();
    }

    @Transient
    @JsonIgnore
    public Integer getMaxMMDistricts(Ethnicity ethnicity) {

        int ethnicityPopulation = getPopulation(ethnicity, PopulationType.TOTAL_POPULATION);
        int totalPopulation = getTotalPopulation(PopulationType.TOTAL_POPULATION);

        return (int) (((double) ethnicityPopulation / (double) totalPopulation) * (double) getNumberDistricts());
    }

    @Transient
    @JsonIgnore
    public Map<PopulationType, Boolean> getPopulationTypeAvailablability() {

        District district = districts.iterator().next();
        Precinct precinct = district.getPrecincts().iterator().next();

        Map<PopulationType, Boolean> populationAvailability = new HashMap<>();
//        TODO: Dont use for each, do map and the collect.
        Arrays.stream(PopulationType.values()).forEach(p -> populationAvailability.put(p, precinct.isPopulationTypeAvailable(p)));

        return populationAvailability;
    }

    @Transient
    @JsonIgnore
    public List<List<Long>> getRecombinationJson() {

        return districts.stream()
                .map(District::getPrecinctIds)
                .collect(Collectors.toList());
    }

    @Transient
    @JsonIgnore
    public List<District> getDistrictOrderPopulation() {

        return districts.stream()
                .sorted(Comparator.comparingInt(o -> o.getTotalPopulation(PopulationType.TOTAL_POPULATION)))
                .collect(Collectors.toList());
    }

    @Transient
    @JsonIgnore
    public List<District> orderDistrictsByReference(Districting reference) {

        return GillConstruct.reorderDistrictsByReference(this, reference);
    }

    @Transient
    @JsonIgnore
    public List<District> orderDistrictsByPopulationType(PopulationType populationType) {

        List<District> orderedDistricts = new ArrayList<>(List.copyOf(districts));
        orderedDistricts.sort(Comparator.comparingInt(o -> o.getTotalPopulation(populationType)));

        return orderedDistricts;
    }

    @Transient
    @JsonIgnore
    public Double getPopulationDeviationFromReference(Districting reference) {

        return GillConstruct.populationDeviationFromReference(this, reference);
    }

    @Transient
    @JsonIgnore
    public Double getAreaDeviationFromReference(Districting reference) {

        return GillConstruct.areaDeviationFromReference(this, reference);
    }

//    @Transient
//    @JsonIgnore
//    public List<District> getDistrictOrderGillConstruct(Districting comparatorDistricting){
//
//
//    }

    @Transient
    @JsonIgnore
    public Double getGeometricCompactness() {

        return GeometryCalculation.getGeometricCompactness(this);
    }

    @Transient
    @JsonIgnore
    public JSONObject getGeometry() {

//        List<JSONObject> districtingArrayList = districts.stream()
//                .map(district -> GeometryCalculation.geometry2Json(district.getGeometry()))
//                .collect(Collectors.toList());
//
//        JSONArray features = new JSONArray();
//        features.addAll(districtingArrayList);
//
//        JSONObject output = new JSONObject();
//        output.put("features", features);
//        output.put("type", "FeatureCollection");
//        return output;

        return GeometryCalculation.calculateDistrictingGeometry(this.getPrecinctIds());
    }

    @Transient
    @JsonIgnore
    public Integer getNumberPrecincts() {

        return districts.stream().map(District::getNumberPrecincts).reduce(0, Integer::sum);
    }

    @Transient
    @JsonIgnore
    public List<List<Long>> getPrecinctIds(){

        return districts.stream().map(District::getPrecinctIds).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Districting{" +
                "districtingId=" + districtingId +
                ", districts=" + districts +
                '}';
    }
}
