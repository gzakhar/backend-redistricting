package edu.stonybrook.redistricting.lemonkeredistricting.models;

import javax.persistence.*;

//@Table(name="state")
@Entity
public class State {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private String name;
    private Long enacted_districting_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getEnacted_districting_id() {
        return enacted_districting_id;
    }

    public void setEnacted_districting_id(Long enacted_districting_id) {
        this.enacted_districting_id = enacted_districting_id;
    }

    @Override
    public String toString() {
        return "State{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", enacted_districting_id=" + enacted_districting_id +
                '}';
    }
}
