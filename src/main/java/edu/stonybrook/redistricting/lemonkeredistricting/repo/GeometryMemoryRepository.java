package edu.stonybrook.redistricting.lemonkeredistricting.repo;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.geojson.GeoJsonReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class GeometryMemoryRepository {

    @Autowired
    private GeometryRepository geometryRepository;

    private static final GeoJsonReader reader = new GeoJsonReader();

    private static Map<Long, Geometry> precinctGeometryMap;

    @PostConstruct
    private void construct() {

        precinctGeometryMap = new ConcurrentHashMap<>();

        geometryRepository.findAll().forEach(p -> {
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