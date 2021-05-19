package edu.stonybrook.redistricting.lemonkeredistricting.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class DistrictStat {

    @Id
    @Column(name = "district_id")
    private Long district_id;
    @Column(name = "districting_id")
    private Long districting_id;
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
    @Column(name = "total_pop")
    private Integer totPop;


    public Long getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(Long district_id) {
        this.district_id = district_id;
    }

    public Long getDistricting_id() {
        return districting_id;
    }

    public void setDistricting_id(Long districting_id) {
        this.districting_id = districting_id;
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
    public Integer getTotalPopulationByEthnicity(Ethnicity ethnicity) {
        switch (ethnicity) {
            case WHITE:
                return getTotWhite();
            case BLACK:
                return getTotBlack();
            case HISPANIC:
                return getTotHisp();
            case ASIAN:
                return getTotAsian();
            case AMERICAN_INDIAN:
                return getTotAIndian();
            case OTHER:
                return getTotOther();
            default:
                return null;
        }
    }

    @Transient
    @JsonIgnore
    public Double getPopulationPercentage(Ethnicity ethnicity) {

        return (double) getTotalPopulationByEthnicity(ethnicity) / totPop;
    }

}
