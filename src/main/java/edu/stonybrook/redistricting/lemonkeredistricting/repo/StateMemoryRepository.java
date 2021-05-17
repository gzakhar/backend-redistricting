package edu.stonybrook.redistricting.lemonkeredistricting.repo;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class StateMemoryRepository {

    static Map<Integer, String> stateIdMap = Stream.of(new String[][]{
            {"0", "unionOfNY.json"},
//            {"1", "Florida"},
            {"2", "unionOfNV.json"}
    }).collect(Collectors.toMap(data -> Integer.parseInt(data[0]), data -> data[1]));

    static JSONParser parser = new JSONParser();

    public static JSONObject getStateGeometry(Long stateId) {

        String fileName = Objects.requireNonNull(stateIdMap.get(stateId.intValue()));

        InputStream inputStream = StateMemoryRepository.class.getResourceAsStream("/state_union/" + fileName);
        Reader reader = new InputStreamReader(inputStream);
        try {
            return (JSONObject) parser.parse(reader);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
}
