package edu.stonybrook.redistricting.lemonkeredistricting.models;

public class DistrictingScore {

    private Long districtingId;
    private Double objectiveScore;

    public DistrictingScore(Long districtingId, Double objectiveScore) {
        this.districtingId = districtingId;
        this.objectiveScore = objectiveScore;
    }

    public Long getDistrictingId() {
        return districtingId;
    }

    public void setDistrictingId(Long districtingId) {
        this.districtingId = districtingId;
    }

    public Double getObjectiveScore() {
        return objectiveScore;
    }

    public void setObjectiveScore(Double objectiveScore) {
        this.objectiveScore = objectiveScore;
    }

    @Override
    public String toString() {
        return "DistrictingScores{" +
                "districtingId=" + districtingId +
                ", objectiveScore=" + objectiveScore +
                '}';
    }
}
