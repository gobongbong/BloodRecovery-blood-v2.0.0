package com.potatoes.converter;


import com.potatoes.constants.RequestStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class RequestStatusConverter implements AttributeConverter<RequestStatus, String> {
    @Override
    public String convertToDatabaseColumn(RequestStatus attribute) {
        return attribute==null ? null : attribute.getValue();
    }

    @Override
    public RequestStatus convertToEntityAttribute(String dbData) {
        return dbData==null ? null : RequestStatus.find(dbData);
    }
}
