package edu.stonybrook.redistricting.lemonkeredistricting.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;

import javax.persistence.*;

@Entity
@Table(name = "districting_summary")
public class DistrictingSummary {

    @Id
    @Column(name = "districting_summary_id")
    private Long districtingSummaryId;

    @Column(name = "district_count")
    private Long districtCount;

    @Column(name = "geometric_compactness")
    private Double geometricCompactness;

    @Column(name = "graph_compactness")
    private Double graphCompactness;

    @Column(name = "population_compactness")
    private Double populationCompactness;

    @Column(name = "mm_white")
    private Long mmWhite;

    @Column(name = "mm_black")
    private Long mmBlack;

    @Column(name = "mm_hispanic")
    private Long mmHispanic;

    @Column(name = "mm_asian")
    private Long mmAsian;

    @Column(name = "mm_amind")
    private Long mmAmind;

    @Column(name = "mm_other")
    private Long mmOther;

    @Column(name = "total_population")
    private Double totalPopulation;

    @Column(name = "va_population")
    private Double vaPopulation;

    @Column(name = "cva_population")
    private Double cvaPopulation;

    @Column(name = "total_population_equality")
    private Double totalPopulationEquality;

    @Column(name = "va_population_equality")
    private Double vaPopulationEquality;

    @Column(name = "cva_population_equality")
    private Double cvaPopulationEquality;


    public DistrictingSummary() {
    }

    public DistrictingSummary(Long districtingSummaryId, Long districtCount, Double geometricCompactness, Double graphCompactness, Double populationCompactness, Long mmWhite, Long mmBlack, Long mmHispanic, Long mmAsian, Long mmAmind, Long mmOther, Double totalPopulation, Double vaPopulation, Double cvaPopulation, Double totalPopulationEquality, Double vaPopulationEquality, Double cvaPopulationEquality) {
        this.districtingSummaryId = districtingSummaryId;
        this.districtCount = districtCount;
        this.geometricCompactness = geometricCompactness;
        this.graphCompactness = graphCompactness;
        this.populationCompactness = populationCompactness;
        this.mmWhite = mmWhite;
        this.mmBlack = mmBlack;
        this.mmHispanic = mmHispanic;
        this.mmAsian = mmAsian;
        this.mmAmind = mmAmind;
        this.mmOther = mmOther;
        this.totalPopulation = totalPopulation;
        this.vaPopulation = vaPopulation;
        this.cvaPopulation = cvaPopulation;
        this.totalPopulationEquality = totalPopulationEquality;
        this.vaPopulationEquality = vaPopulationEquality;
        this.cvaPopulationEquality = cvaPopulationEquality;
    }

    public Long getDistrictingSummaryId() {
        return districtingSummaryId;
    }

    public void setDistrictingSummaryId(Long districtingSummaryId) {
        this.districtingSummaryId = districtingSummaryId;
    }

    public Long getDistrictCount() {
        return districtCount;
    }

    public void setDistrictCount(Long districtCount) {
        this.districtCount = districtCount;
    }

    public Double getGeometricCompactness() {
        return geometricCompactness;
    }

    public void setGeometricCompactness(Double geometricCompactness) {

        this.geometricCompactness = geometricCompactness;
    }

    public Double getGraphCompactness() {
        return graphCompactness;
    }

    public void setGraphCompactness(Double graphCompactness) {
        this.graphCompactness = graphCompactness;
    }

    public Double getPopulationCompactness() {
        return populationCompactness;
    }

    public void setPopulationCompactness(Double populationCompactness) {
        this.populationCompactness = populationCompactness;
    }

    public Long getMmWhite() {
        return mmWhite;
    }

    public void setMmWhite(Long mmWhite) {
        this.mmWhite = mmWhite;
    }

    public Long getMmBlack() {
        return mmBlack;
    }

    public void setMmBlack(Long mmBlack) {
        this.mmBlack = mmBlack;
    }

    public Long getMmHispanic() {
        return mmHispanic;
    }

    public void setMmHispanic(Long mmHispanic) {
        this.mmHispanic = mmHispanic;
    }

    public Long getMmAsian() {
        return mmAsian;
    }

    public void setMmAsian(Long mmAsian) {
        this.mmAsian = mmAsian;
    }

    public Long getMmAmind() {
        return mmAmind;
    }

    public void setMmAmind(Long mmAmind) {
        this.mmAmind = mmAmind;
    }

    public Long getMmOther() {
        return mmOther;
    }

    public void setMmOther(Long mmOther) {
        this.mmOther = mmOther;
    }

    public Double getTotalPopulation() {
        return totalPopulation;
    }

    public void setTotalPopulation(Double totalPopulation) {
        this.totalPopulation = totalPopulation;
    }

    public Double getVaPopulation() {
        return vaPopulation;
    }

    public void setVaPopulation(Double vaPopulation) {
        this.vaPopulation = vaPopulation;
    }

    public Double getCvaPopulation() {
        return cvaPopulation;
    }

    public void setCvaPopulation(Double cvaPopulation) {
        this.cvaPopulation = cvaPopulation;
    }

    public Double getTotalPopulationEquality() {
        return totalPopulationEquality;
    }

    public void setTotalPopulationEquality(Double totalPopulationEquality) {
        this.totalPopulationEquality = totalPopulationEquality;
    }

    public Double getVaPopulationEquality() {
        return vaPopulationEquality;
    }

    public void setVaPopulationEquality(Double vaPopulationEquality) {
        this.vaPopulationEquality = vaPopulationEquality;
    }

    public Double getCvaPopulationEquality() {
        return cvaPopulationEquality;
    }

    public void setCvaPopulationEquality(Double cvaPopulationEquality) {
        this.cvaPopulationEquality = cvaPopulationEquality;
    }

    @Transient
    @JsonIgnore
    public Double getCompactnessByCompactnessType(CompactnessType compactnessType){

        switch (compactnessType) {
            case GEOMETRIC:
                return getGeometricCompactness();
            case GRAPH:
                return getGraphCompactness();
            case POPULATION:
                return getPopulationCompactness();
            default:
                return null;
        }
    }

    @Transient
    @JsonIgnore
    public Double getPopulationDifferenceByPopulationType(PopulationType populationType){

        switch (populationType) {
            case TOTAL_POPULATION:
                return getTotalPopulation();
            case VOTING_AGE_POPULATION:
                return getVaPopulation();
            case CITIZEN_VOTING_AGE_POPULATION:
                return getCvaPopulation();
            default:
                return null;
        }
    }

    @Transient
    @JsonIgnore
    public Double getPopulationEqualityByPopulationType(PopulationType populationType){

        return null;
    }

    @Override
    public String toString() {
        return "DistrictingSummary{" +
                "districtingSummaryId=" + districtingSummaryId +
                ", districtCount=" + districtCount +
                ", geometricCompactness=" + geometricCompactness +
                ", graphCompactness=" + graphCompactness +
                ", populationCompactness=" + populationCompactness +
                ", mmWhite=" + mmWhite +
                ", mmBlack=" + mmBlack +
                ", mmHispanic=" + mmHispanic +
                ", mmAsian=" + mmAsian +
                ", mmAmind=" + mmAmind +
                ", mmOther=" + mmOther +
                ", totalPopulation=" + totalPopulation +
                ", vaPopulation=" + vaPopulation +
                ", cvaPopulation=" + cvaPopulation +
                '}';
    }
}
