package ru.otus.shurupov.jointpurchase.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.AttributeConverter;
import java.io.IOException;
import java.util.Map;

public class MapConverter implements AttributeConverter<Map<String, Object>, String> {

    private static final Logger logger = LoggerFactory.getLogger(MapConverter.class);

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Map<String, Object> attribute) {

        String dbData = null;
        try {
            dbData = objectMapper.writeValueAsString(attribute);
        } catch (final JsonProcessingException e) {
            logger.error("JSON writing error", e);
        }

        return dbData;
    }

    @Override
    public Map<String, Object> convertToEntityAttribute(String dbData) {

        Map<String, Object> customerInfo = null;
        try {
            customerInfo = objectMapper.readValue(dbData, Map.class);
        } catch (final IOException e) {
            logger.error("JSON reading error", e);
        }

        return customerInfo;
    }

}
