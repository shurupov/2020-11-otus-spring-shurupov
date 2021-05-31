package ru.otus.shurupov.jointpurchase.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.shurupov.jointpurchase.domain.UserRole;

import javax.persistence.AttributeConverter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class UserRoleListConverter implements AttributeConverter<List<UserRole>, String> {

    private static final Logger logger = LoggerFactory.getLogger(UserRoleListConverter.class);

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<UserRole> attribute) {
        String dbData = null;
        try {
            dbData = objectMapper.writeValueAsString(
                    attribute.stream()
                        .map(r -> r.toString())
                        .collect(Collectors.toList())
            );
        } catch (final JsonProcessingException e) {
            logger.error("JSON writing error", e);
        }

        return dbData;
    }

    @Override
    public List<UserRole> convertToEntityAttribute(String dbData) {
        List<UserRole> customerInfo = null;
        try {
            customerInfo = (List<UserRole>) objectMapper.readValue(dbData, List.class)
                    .stream()
                    .map(r -> UserRole.valueOf((String) r))
                    .collect(Collectors.toList());
        } catch (final IOException e) {
            logger.error("JSON reading error", e);
        }

        return customerInfo;
    }
}
