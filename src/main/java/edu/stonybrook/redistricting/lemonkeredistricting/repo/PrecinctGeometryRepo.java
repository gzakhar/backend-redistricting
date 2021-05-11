package edu.stonybrook.redistricting.lemonkeredistricting.repo;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.geojson.GeoJsonReader;
import org.locationtech.jts.io.geojson.GeoJsonWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Repository
public class PrecinctGeometryRepo {

    private static Map<Long, Geometry> precinctGeometryMap;

    @Autowired
    private PrecinctGeometryRepository precinctGeometryRepository;

    private static final GeoJsonReader reader = new GeoJsonReader();

    private static final JSONParser parser = new JSONParser();

    private static final GeoJsonWriter writer = new GeoJsonWriter();

    @PostConstruct
    private void construct() {

        precinctGeometryMap = new HashMap<>();

        precinctGeometryRepository.findAll().forEach(p -> {
            Geometry geometry = null;
            try {
                geometry = reader.read(p.getGeometry());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            precinctGeometryMap.put(p.getPrecinctId(), geometry);
        });
    }

    public static Geometry getPrecinctGeometry(long precinctId) {

        return precinctGeometryMap.get(precinctId);
    }

    public static JSONObject getPrecinctJson(long precinctId) {

        try {
            return (JSONObject) parser.parse(writer.write(precinctGeometryMap.get(precinctId)));
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}