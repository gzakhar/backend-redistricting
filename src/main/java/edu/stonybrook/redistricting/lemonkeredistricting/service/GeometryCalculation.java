package edu.stonybrook.redistricting.lemonkeredistricting.service;

import edu.stonybrook.redistricting.lemonkeredistricting.models.Districting;
import edu.stonybrook.redistricting.lemonkeredistricting.repo.DistrictRepository;
import edu.stonybrook.redistricting.lemonkeredistricting.repo.DistrictingRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryCollection;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.io.geojson.GeoJsonReader;
import org.locationtech.jts.io.geojson.GeoJsonWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class GeometryCalculation {

    @Autowired
    private DistrictingRepository districtingRepository;


    //Converts single/multiple precinct/s information to geojson.
    private static JSONObject getGeojsons(Integer[] ids, JSONObject precinctJson) {

        JSONObject output = new JSONObject();
        output.put("type", "FeatureCollection");
        JSONArray features = new JSONArray();

        for (int id : ids) {
            JSONObject precinct = (JSONObject) precinctJson.get(Integer.toString(id));
            features.add(precinct);
        }

        output.put("features", features);

        return output;
    }

    //exports JSONObject as .json file
    private static void exportGeojson(JSONObject output, String name) throws IOException {

        FileWriter file;
        file = new FileWriter(name + ".json");
        file.write(output.toJSONString());
        System.out.println("File successfully exported to JSON");
        try {
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //get Array of geometries for precincts in a district.
    private static Geometry[] getDistrictGeometries(JSONObject districtJsons) {
        JSONArray features = (JSONArray) districtJsons.get("features");
        int numPrecincts = features.size();
        Geometry[] output = new Geometry[numPrecincts];
        GeoJsonReader reader = new GeoJsonReader();
        for (int i = 0; i < numPrecincts; i++) {
            JSONObject feature = (JSONObject) features.get(i);
            try {
                Geometry geometry = reader.read(feature.get("geometry").toString());
                output[i] = geometry;
            } catch (org.locationtech.jts.io.ParseException e) {
                e.printStackTrace();
            }
        }
        return output;
    }

    //Converts multiple precincts information to union of geojson.
    private static JSONObject getUnionGeojsons(Geometry[] geometries) {
        GeometryCollection geometryCollection = new GeometryCollection(geometries, new GeometryFactory());
        Geometry union = geometryCollection.union();
        JSONParser parser = new JSONParser();
        GeoJsonWriter writer = new GeoJsonWriter();
        try {
            JSONObject geometry = (JSONObject) parser.parse(writer.write(union));
            JSONArray cdn = (JSONArray) geometry.get("coordinates");
            if (((String) geometry.get("type")).equals("MultiPolygon")) {
                cdn = (JSONArray) cdn.get(0);
            }
            JSONObject outGeometry = new JSONObject();
            outGeometry.put("coordinates", cdn);
            outGeometry.put("type", "Polygon");
            return outGeometry;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public JSONObject calculateDistrictingGeometry(long districtingId) throws IOException, ParseException {

        JSONParser parser = new JSONParser();

        InputStream precinctJsonIn = GeometryCalculation.class.getResourceAsStream("/NYPopVer5.json");
        Reader PrecinctReader = new InputStreamReader(precinctJsonIn);
        JSONObject precinctGeoJson = (JSONObject) parser.parse(PrecinctReader);

        List<Integer[]> precinctIdsArrayList = Objects
                .requireNonNull(districtingRepository.findById(districtingId).orElse(null))
                .getRecombinationJson();

        long start = System.currentTimeMillis();

        List<JSONObject> districtJsonArray = precinctIdsArrayList.stream()
                .map(precinctIdsArray -> getGeojsons(precinctIdsArray, precinctGeoJson))
                .collect(Collectors.toList());


        List<JSONObject> districtingArrayList = districtJsonArray.parallelStream()
                .map(GeometryCalculation::getDistrictGeometries)
                .map(GeometryCalculation::getUnionGeojsons)
                .collect(Collectors.toList());


        JSONArray features = new JSONArray();
        for (JSONObject districtJson : districtingArrayList)
            features.add(districtJson);

        JSONObject output = new JSONObject();
        output.put("features", features);
        output.put("type", "FeatureCollection");

        long end = System.currentTimeMillis();
        System.out.println("CALCULATION TIME: " + (end - start));
        return output;

    }

}
