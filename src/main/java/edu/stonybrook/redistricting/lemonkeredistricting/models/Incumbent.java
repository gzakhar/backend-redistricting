package edu.stonybrook.redistricting.lemonkeredistricting.models;

import javax.persistence.*;

@Entity
@Table(name = "incumbent")
public class Incumbent {

    private Long incumbentId;
    private Long stateId;
    private String firstName;
    private String lastName;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "incumbent_id")
    public Long getIncumbentId() {
        return incumbentId;
    }

    public void setIncumbentId(Long incumbentId) {
        this.incumbentId = incumbentId;
    }

    @Column(name = "state_id")
    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
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

    @Override
    public String toString() {
        return "Incumbent{" +
                "incumbentId=" + incumbentId +
                ", stateId=" + stateId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
