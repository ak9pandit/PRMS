package com.ashish.PRMS.property.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PropertyResponse {

    private Long id;
    private String name;
    private String addressLine1;
    private String city;
    private String state;
    private String pincode;

}
