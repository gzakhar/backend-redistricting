package edu.stonybrook.redistricting.lemonkeredistricting.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "state")
public class StateSummary {

    private Long stateId;
    private String name;

    @Id
    @Column(name = "state_id")
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
