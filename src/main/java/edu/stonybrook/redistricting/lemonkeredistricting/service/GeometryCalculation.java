package edu.stonybrook.redistricting.lemonkeredistricting.service;

import edu.stonybrook.redistricting.lemonkeredistricting.models.District;
import edu.stonybrook.redistricting.lemonkeredistricting.models.Precinct;
import edu.stonybrook.redistricting.lemonkeredistricting.repo.DistrictingRepository;
import edu.stonybrook.redistricting.lemonkeredistricting.repo.GeometryMemoryRepository;
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
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class GeometryCalculation {

    @Autowired
    private DistrictingRepository districtingRepository;

    private static JSONParser parser = new JSONParser();
    private static GeoJsonWriter writer = new GeoJsonWriter();

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
//                TODO: figure out the Precision Model.
//                geometry = GeometryPrecisionReducer.reduce(geometry, new PrecisionModel(1000));
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

    public static JSONObject geometryToJson(Geometry geometry) {
        JSONParser parser = new JSONParser();
        GeoJsonWriter writer = new GeoJsonWriter();
        try {
            JSONObject json = (JSONObject) parser.parse(writer.write(geometry));
            JSONArray cdn = (JSONArray) json.get("coordinates");
            if (((String) json.get("type")).equals("MultiPolygon")) {
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

//        JSONParser parser = new JSONParser();
//
//        InputStream precinctJsonIn = GeometryCalculation.class.getResourceAsStream("/NYPopVer5.json");
//        Reader PrecinctReader = new InputStreamReader(precinctJsonIn);
//        JSONObject precinctGeoJson = GeometryState.NY.getPrecinctGeoJson();
//        TODO: state precinct geometry file.
//        JSONObject precinctGeoJson = Objects.requireNonNull(precinctGeometryRepo.find(4l).orElse(null)).getGeometry();
        JSONObject precinctGeoJson = null;

//        List<Integer[]> precinctIdsArrayList = Objects
//                .requireNonNull(districtingRepository.findById(districtingId).orElse(null))
//                .getRecombinationJson();


        List<Integer[]> precinctIdsArrayList = new ArrayList<>();
        Collection<District> districts = Objects.requireNonNull(districtingRepository.findById(districtingId).orElse(null)).getDistricts();

        for (District district : districts) {
            Integer[] pArray = new Integer[district.getPrecincts().size()];
            int i = 0;
            for (Precinct p : district.getPrecincts()) {
                pArray[i++] = p.getPrecinctId().intValue();
            }
            precinctIdsArrayList.add(pArray);
        }


        long start = System.currentTimeMillis();

        List<JSONObject> districtJsonArray = precinctIdsArrayList.stream()
                .map(precinctIdsArray -> getGeojsons(precinctIdsArray, precinctGeoJson))
                .collect(Collectors.toList());


        List<JSONObject> districtingArrayList = districtJsonArray.parallelStream()
                .map(GeometryCalculation::getDistrictGeometries)
                .map(GeometryCalculation::getUnionGeojsons)
                .collect(Collectors.toList());


        JSONArray features = new JSONArray();
        for (JSONObject districtJson : districtingArrayList) features.add(districtJson);

        JSONObject output = new JSONObject();
        output.put("features", features);
        output.put("type", "FeatureCollection");

        long end = System.currentTimeMillis();
        System.out.println("CALCULATION TIME: " + (end - start));

        return output;
    }

    public JSONObject calculatePrecinctGeometry(long stateId) throws IOException, ParseException {

        JSONParser parser = new JSONParser();

        InputStream precinctJsonIn = GeometryCalculation.class.getResourceAsStream("/NYPopVer5.json");
        Reader PrecinctReader = new InputStreamReader(precinctJsonIn);
        return (JSONObject) parser.parse(PrecinctReader);
    }

    public static JSONObject geometry2Json(Geometry geometry) {

//        TODO: Im assuming that here @Harlam is attempting to get rid of the geometry that isnt needed. this should be looked at again.
        try {
            JSONObject json = (JSONObject) parser.parse(writer.write(geometry));
            JSONArray cdn = (JSONArray) json.get("coordinates");
            if (((String) json.get("type")).equals("MultiPolygon")) {
                cdn = (JSONArray) cdn.get(0);
            }
            JSONObject outGeometry = new JSONObject();
            outGeometry.put("coordinates", cdn);
            outGeometry.put("type", "Polygon");
            return outGeometry;
        } catch (ParseException e) {
            e.printStackTrace();
        }

//        TODO: This is a regular conversion, just reading the Geometry into JsonObject.
//        try {
//            return (JSONObject) parser.parse(writer.write(geometry));
//        } catch (org.json.simple.parser.ParseException e) {
//            System.err.println("Error Converting Geometry");
//            e.printStackTrace();
//        }

        return null;
    }

}
