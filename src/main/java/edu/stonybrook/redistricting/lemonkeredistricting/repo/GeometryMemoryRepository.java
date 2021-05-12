package edu.stonybrook.redistricting.lemonkeredistricting.repo;

import edu.stonybrook.redistricting.lemonkeredistricting.service.GeometryCalculation;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.geojson.GeoJsonReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Repository
public class GeometryMemoryRepository {

    private static Map<Long, Geometry> precinctGeometryMap;

    @Autowired
    private GeometryRepository geometryRepository;

    private static final GeoJsonReader reader = new GeoJsonReader();

    @PostConstruct
    private void construct() {

        precinctGeometryMap = new HashMap<>();

//        TODO: only getting Nevada.
        geometryRepository.findAllByStateId(4).forEach(p -> {
            try {
                Geometry geometry = reader.read(p.getGeometry().toString());
                precinctGeometryMap.put(p.getPrecinctId(), geometry);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
    }

    public static Geometry getPrecinctGeometry(long precinctId) {

        return precinctGeometryMap.get(precinctId);
    }

}