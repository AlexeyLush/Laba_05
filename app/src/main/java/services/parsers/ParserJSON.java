package services.parsers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import exception.ParserException;
import models.LabWork;
import services.parsers.interfaces.ParserElement;
import services.parsers.interfaces.ParserMap;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 * Класс для парсинга JSON файлов
 */

public class ParserJSON implements ParserElement<LabWork>, ParserMap<String, LabWork> {
    private final ObjectMapper mapper;

    public ParserJSON(){
        mapper = new ObjectMapper();
        mapper.registerModule(new JSR310Module());
        mapper.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
    }

    public boolean isDeserializeElement(String json){
        boolean isTrue = true;
        try {
            TypeReference<LabWork> typeRef = new TypeReference<LabWork>() {};
            mapper.readValue(json, typeRef);
        } catch (IOException e) {
            isTrue = false;
        }
        return isTrue;
    }

    @Override
    public LabWork deserializeElement(String json) {
        try {
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
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(elements);
        } catch (JsonProcessingException e) {
            new ParserException().outputException();
        }
        return json;
    }

    @Override
    public Map<String, LabWork> deserializeMap(String json) {
        try {
            TypeReference<LinkedHashMap<Integer, LabWork>> typeRef = new TypeReference<LinkedHashMap<Integer, LabWork>>() {};
            return new LinkedHashMap<>(mapper.readValue(json, typeRef));
        } catch (IOException e) {
            new ParserException().outputException();
        }
        return new LinkedHashMap<>();
    }

    @Override
    public String serializeMap(Map<String, LabWork> elements) {

        String json = "";
        try {
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(elements);
        } catch (JsonProcessingException e) {
            new ParserException().outputException();
        }
        return json;
    }
}
