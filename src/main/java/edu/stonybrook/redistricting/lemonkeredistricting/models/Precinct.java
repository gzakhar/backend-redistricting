package edu.stonybrook.redistricting.lemonkeredistricting.models;

import edu.stonybrook.redistricting.lemonkeredistricting.converter.JsonToMapConverter;

import javax.persistence.*;
import java.util.Collection;
import java.util.Map;

//@Entity
//@Table(name = "precinct")
public class Precinct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long precinctId;
//    @Column(name = "geo_json")
//    @Convert(attributeName = "data", converter = JsonToMapConverter.class)
//    private Map<String, Object> geoJson;
//    private Map<Ethnicity, Long> totalPopulation;
//    private Map<Ethnicity, Long> totalVotingAgePopulation;
//    private Map<Ethnicity, Long> citizenVotingAgePopulation;
//    private Collection<Precinct> neighbors;


    public Long getPrecinctId() {
        return precinctId;
    }

    public void setPrecinctId(Long precinctId) {
        this.precinctId = precinctId;
    }

//    public Map<String, Object> getGeoJson() {
//        return geoJson;
//    }
//
//    public void setGeoJson(Map<String, Object> geoJson) {
//        this.geoJson = geoJson;
//    }
//
//    public Map<Ethnicity, Long> getTotalPopulation() {
//        return totalPopulation;
//    }
//
//    public void setTotalPopulation(Map<Ethnicity, Long> totalPopulation) {
//        this.totalPopulation = totalPopulation;
//    }
//
//    public Map<Ethnicity, Long> getTotalVotingAgePopulation() {
//        return totalVotingAgePopulation;
//    }
//
//    public void setTotalVotingAgePopulation(Map<Ethnicity, Long> totalVotingAgePopulation) {
//        this.totalVotingAgePopulation = totalVotingAgePopulation;
//    }
//
//    public Map<Ethnicity, Long> getCitizenVotingAgePopulation() {
//        return citizenVotingAgePopulation;
//    }
//
//    public void setCitizenVotingAgePopulation(Map<Ethnicity, Long> citizenVotingAgePopulation) {
//        this.citizenVotingAgePopulation = citizenVotingAgePopulation;
//    }
//
//    public Collection<Precinct> getNeighbors() {
//        return neighbors;
//    }
//
//    public void setNeighbors(Collection<Precinct> neighbors) {
//        this.neighbors = neighbors;
//    }
//
//    @Override
//    public String toString() {
//        return "Precinct{" +
//                "precinctId=" + precinctId +
//                ", geoJson=" + geoJson +
//                ", totalPopulation=" + totalPopulation +
//                ", totalVotingAgePopulation=" + totalVotingAgePopulation +
//                ", citizenVotingAgePopulation=" + citizenVotingAgePopulation +
//                ", neighbors=" + neighbors +
//                '}';
//    }
}
