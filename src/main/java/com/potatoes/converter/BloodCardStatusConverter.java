package com.potatoes.converter;


import com.potatoes.constants.BloodCardStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class BloodCardStatusConverter implements AttributeConverter<BloodCardStatus, String> {
    @Override
    public String convertToDatabaseColumn(BloodCardStatus attribute) {
        return attribute==null ? null : attribute.getValue();
    }

    @Override
    public BloodCardStatus convertToEntityAttribute(String dbData) {
        return dbData==null ? null : BloodCardStatus.find(dbData);
    }
}
