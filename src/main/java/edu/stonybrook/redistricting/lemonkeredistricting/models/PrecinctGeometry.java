package edu.stonybrook.redistricting.lemonkeredistricting.models;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.json.simple.JSONObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "precinct")
@TypeDefs(
        @TypeDef(name = "json", typeClass = JsonStringType.class)
)
public class PrecinctGeometry {

    @Id
    @Column(name = "precinct_id")
    private long precinctId;

    @Column(name = "geometry")
    @Type(type = "json")
    private JSONObject geometry;

    public long getPrecinctId() {
        return precinctId;
    }

    public void setPrecinctId(long precinctId) {
        this.precinctId = precinctId;
    }

    public JSONObject getGeometry() {
        return geometry;
    }

    public void setGeometry(JSONObject geometry) {
        this.geometry = geometry;
    }

    @Override
    public String toString() {
        return "PrecinctGeometry{" +
                "precinctId=" + precinctId +
                ", geometry='" + geometry + '\'' +
                '}';
    }
}
