package com.ashish.PRMS.property.service;

import com.ashish.PRMS.auth.entity.User;
import com.ashish.PRMS.auth.repository.UserRepository;
import com.ashish.PRMS.property.dto.CreatePropertyRequest;
import com.ashish.PRMS.property.dto.PropertyResponse;
import com.ashish.PRMS.property.entity.Property;
import com.ashish.PRMS.property.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements  PropertyService {

    private final PropertyRepository propertyRepository;
    private final UserRepository userRepository;

    @Override
    public PropertyResponse createProperty(CreatePropertyRequest createPropertyRequest) {

        User owner = getLoggedInUser();
        Property property = Property.builder()
                .name(createPropertyRequest.getName())
                .addressLine1(createPropertyRequest.getAddressLine1())
                .city(createPropertyRequest.getCity())
                .state(createPropertyRequest.getState())
                .pincode(createPropertyRequest.getPincode())
                .owner(owner)
                .build();

        Property savedProperty = propertyRepository.save(property);
        return mapToResponse(savedProperty);
    }

    private PropertyResponse mapToResponse(Property property) {
        return PropertyResponse.builder()
                .id(property.getId())
                .name(property.getName())
                .addressLine1(property.getAddressLine1())
                .city(property.getCity())
                .state(property.getState())
                .pincode(property.getPincode())
                .build();
    }

    @Override
    public List<PropertyResponse> getMyProperties() {
        User owner = getLoggedInUser();
        List<PropertyResponse> propertyResponseList = propertyRepository.findByOwner(owner)
                .stream()
                .map(p -> mapToResponse(p))
                .toList();

        return propertyResponseList;
    }

    @Override
    public PropertyResponse getPropertyById(Long id) {
        User owner = getLoggedInUser();
        Property property = propertyRepository.findByIdAndOwner(id, owner)
                .orElseThrow(() -> new RuntimeException("Property with id " + id + " not found"));
        return mapToResponse(property);
    }

    @Override
    public PropertyResponse updateProperty(Long id, CreatePropertyRequest request) {
        User user = getLoggedInUser();
        Property property = propertyRepository.findByIdAndOwner(id, user)
                .orElseThrow(() -> new RuntimeException("Property with id " + id + " not found"));
        property.setName(request.getName());
        property.setAddressLine1(request.getAddressLine1());
        property.setCity(request.getCity());
        property.setState(request.getState());
        property.setPincode(request.getPincode());

        Property savedProperty = propertyRepository.save(property);
        return mapToResponse(savedProperty);
    }

    private User getLoggedInUser(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();

        return userRepository.findByEmail(name).orElseThrow(()-> new RuntimeException("User not found"));
    }
}
