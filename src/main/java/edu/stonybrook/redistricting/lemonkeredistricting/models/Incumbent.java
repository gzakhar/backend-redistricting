package edu.stonybrook.redistricting.lemonkeredistricting.models;

import javax.persistence.*;

@Entity
@Table(name = "incumbent")
public class Incumbent {

    @Id
    @Column(name = "incumbent_id")
    private Long incumbentId;
    @Column(name = "state_id")
    private Long stateId;
    @Column(name = "precinct_id")
    private Long precinctId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    public Long getIncumbentId() {
        return incumbentId;
    }

    public void setIncumbentId(Long incumbentId) {
        this.incumbentId = incumbentId;
    }

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    public Long getPrecinctId() {
        return precinctId;
    }

    public void setPrecinctId(Long precinctId) {
        this.precinctId = precinctId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
