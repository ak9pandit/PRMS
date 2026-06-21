package com.ashish.PRMS.property.controller;

import com.ashish.PRMS.property.dto.CreatePropertyRequest;
import com.ashish.PRMS.property.dto.PropertyResponse;
import com.ashish.PRMS.property.entity.Property;
import com.ashish.PRMS.property.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/properties")
@RequiredArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;

    @PostMapping
    public PropertyResponse createProperty(@RequestBody CreatePropertyRequest propertyRequest){
        PropertyResponse property = propertyService.createProperty(propertyRequest);
        return property;
    }

    @GetMapping
    public List<PropertyResponse> getAllProperties(){
        return propertyService.getMyProperties();
    }

    @GetMapping("/{id}")
    public PropertyResponse getPropertyById(@PathVariable Long id){
        return propertyService.getPropertyById(id);
    }

    @PutMapping("/{id}")
    public PropertyResponse updateProperty(@PathVariable Long id, @RequestBody CreatePropertyRequest request){
        return propertyService.updateProperty(id, request);
    }
}
