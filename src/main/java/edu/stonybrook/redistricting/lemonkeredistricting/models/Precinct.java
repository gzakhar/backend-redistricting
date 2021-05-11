package edu.stonybrook.redistricting.lemonkeredistricting.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import edu.stonybrook.redistricting.lemonkeredistricting.repo.PrecinctGeometryRepo;
import org.hibernate.annotations.TypeDef;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.geojson.GeoJsonReader;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@TypeDef(name = "json", typeClass = JsonBinaryType.class)
public class Precinct {

    private Long precinctId;
    private Long stateId;
    private Integer totWhite;
    private Integer totBlack;
    private Integer totHisp;
    private Integer totAsian;
    private Integer totAIndian;
    private Integer totOther;
    private Integer vapWhite;
    private Integer vapBlack;
    private Integer vapHisp;
    private Integer vapAsian;
    private Integer vapAIndian;
    private Integer vapOther;
    private Integer cvapWhite;
    private Integer cvapBlack;
    private Integer cvapHisp;
    private Integer cvapAsian;
    private Integer cvapAIndian;
    private Integer cvapOther;

    /* getters and setters*/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "precinct_id")
    public Long getPrecinctId() {
        return precinctId;
    }

    public void setPrecinctId(Long precinctId) {
        this.precinctId = precinctId;
    }

    @Column(name = "state_id")
    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    @Column(name = "tot_white")
    public Integer getTotWhite() {
        return totWhite;
    }

    public void setTotWhite(Integer totWhite) {
        this.totWhite = totWhite;
    }

    @Column(name = "tot_black")
    public Integer getTotBlack() {
        return totBlack;
    }

    public void setTotBlack(Integer totBlack) {
        this.totBlack = totBlack;
    }

    @Column(name = "tot_hisp")
    public Integer getTotHisp() {
        return totHisp;
    }

    public void setTotHisp(Integer totHisp) {
        this.totHisp = totHisp;
    }

    @Column(name = "tot_asian")
    public Integer getTotAsian() {
        return totAsian;
    }

    public void setTotAsian(Integer totAsian) {
        this.totAsian = totAsian;
    }

    @Column(name = "tot_a_indian")
    public Integer getTotAIndian() {
        return totAIndian;
    }

    public void setTotAIndian(Integer totAIndian) {
        this.totAIndian = totAIndian;
    }

    @Column(name = "tot_other")
    public Integer getTotOther() {
        return totOther;
    }

    public void setTotOther(Integer totOther) {
        this.totOther = totOther;
    }

    @Column(name = "vap_white")
    public Integer getVapWhite() {
        return vapWhite;
    }

    public void setVapWhite(Integer vapWhite) {
        this.vapWhite = vapWhite;
    }

    @Column(name = "vap_black")
    public Integer getVapBlack() {
        return vapBlack;
    }

    public void setVapBlack(Integer vapBlack) {
        this.vapBlack = vapBlack;
    }

    @Column(name = "vap_hisp")
    public Integer getVapHisp() {
        return vapHisp;
    }

    public void setVapHisp(Integer vapHisp) {
        this.vapHisp = vapHisp;
    }

    @Column(name = "vap_asian")
    public Integer getVapAsian() {
        return vapAsian;
    }

    public void setVapAsian(Integer vapAsian) {
        this.vapAsian = vapAsian;
    }

    @Column(name = "vap_a_indian")
    public Integer getVapAIndian() {
        return vapAIndian;
    }

    public void setVapAIndian(Integer vapAIndian) {
        this.vapAIndian = vapAIndian;
    }

    @Column(name = "vap_other")
    public Integer getVapOther() {
        return vapOther;
    }

    public void setVapOther(Integer vapOther) {
        this.vapOther = vapOther;
    }

    @Column(name = "cvap_white")
    public Integer getCvapWhite() {
        return cvapWhite;
    }

    public void setCvapWhite(Integer cvapWhite) {
        this.cvapWhite = cvapWhite;
    }

    @Column(name = "cvap_black")
    public Integer getCvapBlack() {
        return cvapBlack;
    }

    public void setCvapBlack(Integer cvapBlack) {
        this.cvapBlack = cvapBlack;
    }

    @Column(name = "cvap_hisp")
    public Integer getCvapHisp() {
        return cvapHisp;
    }

    public void setCvapHisp(Integer cvapHisp) {
        this.cvapHisp = cvapHisp;
    }

    @Column(name = "cvap_asian")
    public Integer getCvapAsian() {
        return cvapAsian;
    }

    public void setCvapAsian(Integer cvapAsian) {
        this.cvapAsian = cvapAsian;
    }

    @Column(name = "cvap_a_indian")
    public Integer getCvapAIndian() {
        return cvapAIndian;
    }

    public void setCvapAIndian(Integer cvapAIndian) {
        this.cvapAIndian = cvapAIndian;
    }

    @Column(name = "cvap_other")
    public Integer getCvapOther() {
        return cvapOther;
    }

    public void setCvapOther(Integer cvapOther) {
        this.cvapOther = cvapOther;
    }

    public Boolean isPopulationTypeAvailable(PopulationType populationType) {

        return Arrays.stream(Ethnicity.values()).noneMatch(ethnicity -> getPopulation(ethnicity, populationType) == null);
    }

    public Integer getTotalPopulationType(PopulationType populationType) {

        if (!isPopulationTypeAvailable(populationType))
            return null;

        return Arrays.stream(Ethnicity.values()).map((ethnicity -> getPopulation(ethnicity, populationType))).reduce(0, Integer::sum);
    }

    public Integer getPopulation(Ethnicity ethnicity, PopulationType populationType) {
        switch (populationType) {
            case TOTAL_POPULATION:
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
            case VOTING_AGE_POPULATION:
                switch (ethnicity) {
                    case WHITE:
                        return getVapWhite();
                    case BLACK:
                        return getVapBlack();
                    case HISPANIC:
                        return getVapHisp();
                    case ASIAN:
                        return getVapAsian();
                    case AMERICAN_INDIAN:
                        return getVapAIndian();
                    case OTHER:
                        return getVapOther();
                    default:
                        return null;
                }
            case CITIZEN_VOTING_AGE_POPULATION:
                switch (ethnicity) {
                    case WHITE:
                        return getCvapWhite();
                    case BLACK:
                        return getCvapBlack();
                    case HISPANIC:
                        return getCvapHisp();
                    case ASIAN:
                        return getCvapAsian();
                    case AMERICAN_INDIAN:
                        return getCvapAIndian();
                    case OTHER:
                        return getCvapOther();
                    default:
                        return null;
                }
            default:
                return null;
        }
    }

    @Transient
    @JsonIgnore
    public Geometry getGeometry() {

        return PrecinctGeometryRepo.getPrecinctGeometry(this.precinctId);
    }


    @Override
    public String toString() {
        return "Precinct{" +
                "precinctId=" + precinctId +
                ", stateId=" + stateId +
                ", totWhite=" + totWhite +
                ", totBlack=" + totBlack +
                ", totHisp=" + totHisp +
                ", totAsian=" + totAsian +
                ", totAIndian=" + totAIndian +
                ", totOther=" + totOther +
                ", vapWhite=" + vapWhite +
                ", vapBlack=" + vapBlack +
                ", vapHisp=" + vapHisp +
                ", vapAsian=" + vapAsian +
                ", vapAIndian=" + vapAIndian +
                ", vapOther=" + vapOther +
                ", cvapWhite=" + cvapWhite +
                ", cvapBlack=" + cvapBlack +
                ", cvapHisp=" + cvapHisp +
                ", cvapAsian=" + cvapAsian +
                ", cvapAIndian=" + cvapAIndian +
                ", cvapOther=" + cvapOther +
                '}';
    }
}
