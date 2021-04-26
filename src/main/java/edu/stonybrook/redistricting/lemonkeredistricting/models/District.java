package edu.stonybrook.redistricting.lemonkeredistricting.models;

import java.util.Collection;

public class District {

    private Long districtId;
    private Collection<Precinct> precincts;
    private Integer splitCounties;
    private Collection<Incumbent> incumbents;
    private Boolean mmDistrict;

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public Collection<Precinct> getPrecincts() {
        return precincts;
    }

    public void setPrecincts(Collection<Precinct> precincts) {
        this.precincts = precincts;
    }

    public Integer getSplitCounties() {
        return splitCounties;
    }

    public void setSplitCounties(Integer splitCounties) {
        this.splitCounties = splitCounties;
    }

    public Collection<Incumbent> getIncumbents() {
        return incumbents;
    }

    public void setIncumbents(Collection<Incumbent> incumbents) {
        this.incumbents = incumbents;
    }

    public Boolean getMmDistrict() {
        return mmDistrict;
    }

    public void setMmDistrict(Boolean mmDistrict) {
        this.mmDistrict = mmDistrict;
    }

    @Override
    public String toString() {
        return "District{" +
                "districtId=" + districtId +
                ", precincts=" + precincts +
                ", splitCounties=" + splitCounties +
                ", incumbents=" + incumbents +
                ", mmDistrict=" + mmDistrict +
                '}';
    }
}
