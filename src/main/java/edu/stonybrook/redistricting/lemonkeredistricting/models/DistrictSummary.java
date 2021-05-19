package edu.stonybrook.redistricting.lemonkeredistricting.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "district")
public class DistrictSummary {

    @Id
    @Column(name = "district_id")
    private Long districtId;
    @Column(name = "districting_id")
    private Long districtingId;
    @Column(name = "tot_white")
    private Integer totWhite;
    @Column(name = "tot_black")
    private Integer totBlack;
    @Column(name = "tot_hisp")
    private Integer totHisp;
    @Column(name = "tot_asian")
    private Integer totAsian;
    @Column(name = "tot_a_indian")
    private Integer totAIndian;
    @Column(name = "tot_other")
    private Integer totOther;
    @Column(name = "tot_pop")
    private Integer totPop;

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

    public Integer getTotWhite() {
        return totWhite;
    }

    public void setTotWhite(Integer totWhite) {
        this.totWhite = totWhite;
    }

    public Integer getTotBlack() {
        return totBlack;
    }

    public void setTotBlack(Integer totBlack) {
        this.totBlack = totBlack;
    }

    public Integer getTotHisp() {
        return totHisp;
    }

    public void setTotHisp(Integer totHisp) {
        this.totHisp = totHisp;
    }

    public Integer getTotAsian() {
        return totAsian;
    }

    public void setTotAsian(Integer totAsian) {
        this.totAsian = totAsian;
    }

    public Integer getTotAIndian() {
        return totAIndian;
    }

    public void setTotAIndian(Integer totAIndian) {
        this.totAIndian = totAIndian;
    }

    public Integer getTotOther() {
        return totOther;
    }

    public void setTotOther(Integer totOther) {
        this.totOther = totOther;
    }

    public Integer getTotPop() {
        return totPop;
    }

    public void setTotPop(Integer totPop) {
        this.totPop = totPop;
    }

    @Transient
    @JsonIgnore
    public Boolean isMagorityMinorityByEthnicity(Ethnicity ethnicity, Double threshold) {

        return getAveragePopulationByEthnicity(ethnicity) >= threshold;
    }

    @Transient
    @JsonIgnore
    public Integer getAveragePopulationByEthnicity(Ethnicity ethnicity) {

        switch (ethnicity) {
            case WHITE:
                return totWhite / totPop;
            case BLACK:
                return totBlack / totPop;
            case HISPANIC:
                return totHisp / totPop;
            case ASIAN:
                return totAsian / totPop;
            case AMERICAN_INDIAN:
                return totAIndian / totPop;
            case OTHER:
                return totOther / totPop;
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return "DistrictSummary{" +
                "districtId=" + districtId +
                ", districtingId=" + districtingId +
                ", totWhite=" + totWhite +
                ", totBlack=" + totBlack +
                ", totHisp=" + totHisp +
                ", totAsian=" + totAsian +
                ", totAIndian=" + totAIndian +
                ", totOther=" + totOther +
                ", totPopulation=" + totPop +
                '}';
    }
}

