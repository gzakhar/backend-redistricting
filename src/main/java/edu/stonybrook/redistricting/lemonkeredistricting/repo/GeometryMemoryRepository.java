package edu.stonybrook.redistricting.lemonkeredistricting.repo;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.geojson.GeoJsonReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.*;
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
        //push csv to precinctGeometryMap
        InputStream inputStream = GeometryMemoryRepository.class.getResourceAsStream("/precinct.csv");
        try (CSVReader csvReader = new CSVReader(new InputStreamReader(inputStream))){
            String[] values;
            while ((values = csvReader.readNext()) != null){
                Geometry geometry = reader.read(values[3]);
                Long precinctId = Long.parseLong(values[1]);
                precinctGeometryMap.put(precinctId, geometry);
            }
        } catch (IOException | CsvValidationException | org.locationtech.jts.io.ParseException e) {
            e.printStackTrace();
        }

    }
    public static Geometry getPrecinctGeometry(long precinctId) {

        return precinctGeometryMap.get(precinctId);
    }
}