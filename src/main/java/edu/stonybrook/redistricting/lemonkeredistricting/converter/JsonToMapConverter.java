package edu.stonybrook.redistricting.lemonkeredistricting.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Converter
public class JsonToMapConverter
        implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String dbData) {

        return dbData;

//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//            return objectMapper.writeValueAsString(dbData);
//        } catch (JsonProcessingException e) {
//                System.err.println(e);
//            return null;
//        }
    }


    @Override
    @SuppressWarnings("unchecked")
    public String convertToEntityAttribute(String attribute) {

        return attribute;
//        if (attribute == null) {
//            return new HashMap<>();
//        }
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//            return objectMapper.readValue(attribute, HashMap.class);
//        } catch (IOException e) {
//            System.err.println(e);
//        }
//        return new HashMap<>();
    }

}