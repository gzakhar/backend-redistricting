package edu.stonybrook.redistricting.lemonkeredistricting.models;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import edu.stonybrook.redistricting.lemonkeredistricting.converter.JsonToMapConverter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Entity
@TypeDef(name = "json", typeClass = JsonBinaryType.class)
public class Precinct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "precinct_id")
    private Long precinctId;
    @Column(name = "state_id")
    private Long stateId;
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
    @Column(name = "vap_white")
    private Integer vapWhite;
    @Column(name = "vap_black")
    private Integer vapBlack;
    @Column(name = "vap_hisp")
    private Integer vapHisp;
    @Column(name = "vap_asian")
    private Integer vapAsian;
    @Column(name = "vap_a_indian")
    private Integer vapAIndian;
    @Column(name = "vap_other")
    private Integer vapOther;
    @Column(name = "cvap_white")
    private Integer cvapWhite;
    @Column(name = "cvap_black")
    private Integer cvapBlack;
    @Column(name = "cvap_hisp")
    private Integer cvapHisp;
    @Column(name = "cvap_asian")
    private Integer cvapAsian;
    @Column(name = "cvap_a_indian")
    private Integer cvapAIndian;
    @Column(name = "cvap_other")
    private Integer cvapOther;

//    @Column(name = "geo_json")
//    @Type(type = "json")
//    private Map<String, Object> geometry;

    public Long getPrecinctId() {
        return precinctId;
    }

    public void setPrecinctId(Long precinctId) {
        this.precinctId = precinctId;
    }

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
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

    public Integer getVapWhite() {
        return vapWhite;
    }

    public void setVapWhite(Integer vapWhite) {
        this.vapWhite = vapWhite;
    }

    public Integer getVapBlack() {
        return vapBlack;
    }

    public void setVapBlack(Integer vapBlack) {
        this.vapBlack = vapBlack;
    }

    public Integer getVapHisp() {
        return vapHisp;
    }

    public void setVapHisp(Integer vapHisp) {
        this.vapHisp = vapHisp;
    }

    public Integer getVapAsian() {
        return vapAsian;
    }

    public void setVapAsian(Integer vapAsian) {
        this.vapAsian = vapAsian;
    }

    public Integer getVapAIndian() {
        return vapAIndian;
    }

    public void setVapAIndian(Integer vapAIndian) {
        this.vapAIndian = vapAIndian;
    }

    public Integer getVapOther() {
        return vapOther;
    }

    public void setVapOther(Integer vapOther) {
        this.vapOther = vapOther;
    }

    public Integer getCvapWhite() {
        return cvapWhite;
    }

    public void setCvapWhite(Integer cvapWhite) {
        this.cvapWhite = cvapWhite;
    }

    public Integer getCvapBlack() {
        return cvapBlack;
    }

    public void setCvapBlack(Integer cvapBlack) {
        this.cvapBlack = cvapBlack;
    }

    public Integer getCvapHisp() {
        return cvapHisp;
    }

    public void setCvapHisp(Integer cvapHisp) {
        this.cvapHisp = cvapHisp;
    }

    public Integer getCvapAsian() {
        return cvapAsian;
    }

    public void setCvapAsian(Integer cvapAsian) {
        this.cvapAsian = cvapAsian;
    }

    public Integer getCvapAIndian() {
        return cvapAIndian;
    }

    public void setCvapAIndian(Integer cvapAIndian) {
        this.cvapAIndian = cvapAIndian;
    }

    public Integer getCvapOther() {
        return cvapOther;
    }

    public void setCvapOther(Integer cvapOther) {
        this.cvapOther = cvapOther;
    }

    public Integer getTotalPopulation(){
        return (getTotWhite() + getTotBlack() + getTotHisp() + getTotAsian() + getTotAIndian() + getTotOther());
    }

//    public Map<String, Object> getGeometry() {
//        return geometry;
//    }
//
//    public void setGeometry(Map<String, Object> geometry) {
//        this.geometry = geometry;
//    }
}
