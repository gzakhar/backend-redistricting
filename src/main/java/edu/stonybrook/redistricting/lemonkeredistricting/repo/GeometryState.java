package edu.stonybrook.redistricting.lemonkeredistricting.repo;

import edu.stonybrook.redistricting.lemonkeredistricting.models.PrecinctGeometry;
import edu.stonybrook.redistricting.lemonkeredistricting.models.State;
import edu.stonybrook.redistricting.lemonkeredistricting.service.GeometryCalculation;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.locationtech.jts.geom.Geometry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.beans.Transient;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public enum GeometryState {
    NY("/NYPopVer5.json");

    private JSONObject precinctGeoJson;


    private GeometryState(String file) {
        JSONParser parser = new JSONParser();
        InputStream precinctJsonIn = GeometryCalculation.class.getResourceAsStream(file);
        Reader PrecinctReader = new InputStreamReader(precinctJsonIn);
        try {
            precinctGeoJson = (JSONObject) parser.parse(PrecinctReader);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public JSONObject getPrecinctGeoJson() {
        return precinctGeoJson;
    }
}
