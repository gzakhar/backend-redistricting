package edu.stonybrook.redistricting.lemonkeredistricting.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class StateSummary {

    @Id
    @Column(name = "state_id")
    private Long stateId;
    private String name;

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "StateSummary{" +
                "stateId=" + stateId +
                ", name='" + name + '\'' +
                '}';
    }
}
