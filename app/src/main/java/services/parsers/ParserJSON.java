package services.parsers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import exception.ParserException;
import models.LabWork;
import services.parsers.interfaces.ParserElement;
import services.parsers.interfaces.ParserMap;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class ParserJSON implements ParserElement<LabWork>, ParserMap<Integer, LabWork> {

    @Override
    public LabWork deserializeElement(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<LabWork> typeRef = new TypeReference<LabWork>() {};
            return mapper.readValue(json, typeRef);
        } catch (IOException e) {
            new ParserException().outputException();
        }
        return new LabWork();
    }

    @Override
    public String serializeElement(LabWork elements) {
        String json = "";
        try {
            ObjectMapper mapper = new ObjectMapper();
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(elements);
        } catch (JsonProcessingException e) {
            new ParserException().outputException();
        }
        return json;
    }

    @Override
    public Map<Integer, LabWork> deserializeMap(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<LinkedHashMap<Integer, LabWork>> typeRef = new TypeReference<LinkedHashMap<Integer, LabWork>>() {};
            return new LinkedHashMap<>(mapper.readValue(json, typeRef));
        } catch (IOException e) {
            new ParserException().outputException();
        }
        return new LinkedHashMap<>();
    }

    @Override
    public String serializeMap(Map<Integer, LabWork> elements) {

        String json = "";
        try {
            ObjectMapper mapper = new ObjectMapper();
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(elements);
        } catch (JsonProcessingException e) {
            new ParserException().outputException();
        }
        return json;
    }
}
