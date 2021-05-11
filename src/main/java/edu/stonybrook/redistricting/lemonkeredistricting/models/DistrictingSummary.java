package edu.stonybrook.redistricting.lemonkeredistricting.models;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;

import javax.persistence.*;

@Entity
public class DistrictingSummary {

    @Id
    @Column(name = "districting_summary_id")
    private Long districtingSummaryId;

    @Column(name = "district_count")
    private Integer districtingCount;

    public DistrictingSummary() {
    }

    public DistrictingSummary(long id, int count) {
        this.districtingSummaryId = id;
        this.districtingCount = count;
    }

    public Long getDistrictingSummaryId() {
        return districtingSummaryId;
    }

    public void setDistrictingSummaryId(Long districtingSummaryId) {
        this.districtingSummaryId = districtingSummaryId;
    }

    public Integer getDistrictingCount() {
        return districtingCount;
    }

    public void setDistrictingCount(Integer districtingCount) {
        this.districtingCount = districtingCount;
    }

    @Override
    public String toString() {
        return "DistrictingSummary{" +
                "districtingSummaryId=" + districtingSummaryId +
                ", districtingCount=" + districtingCount +
                '}';
    }
}
