package com.ashish.PRMS.property.service;

import com.ashish.PRMS.property.dto.CreatePropertyRequest;
import com.ashish.PRMS.property.dto.PropertyResponse;
import com.ashish.PRMS.property.entity.Property;

import java.util.List;

public interface PropertyService {

    PropertyResponse createProperty(CreatePropertyRequest createPropertyRequest);

    List<PropertyResponse> getMyProperties();

    PropertyResponse getPropertyById(Long id);

    PropertyResponse updateProperty(Long id, CreatePropertyRequest request);
}
