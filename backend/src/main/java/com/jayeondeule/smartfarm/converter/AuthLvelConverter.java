package com.jayeondeule.smartfarm.converter;

import com.jayeondeule.smartfarm.enums.user.AuthLvel;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class AuthLvelConverter implements AttributeConverter<AuthLvel, Integer> {

    @Override
    public Integer convertToDatabaseColumn(AuthLvel attribute) {
        if (attribute == null) return null;
        return attribute.getCode(); // DB에는 int 저장
    }

    @Override
    public AuthLvel convertToEntityAttribute(Integer dbData) {
        if (dbData == null) return null;
        for (AuthLvel level : AuthLvel.values()) {
            if (level.getCode() == dbData) {
                return level;
            }
        }
        throw new IllegalArgumentException("Unknown auth level code: " + dbData);
    }
}
