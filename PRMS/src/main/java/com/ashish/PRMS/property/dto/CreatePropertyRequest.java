package com.ashish.PRMS.property.dto;

import lombok.Data;

@Data
public class CreatePropertyRequest {

    private String name;
    private String addressLine1;
    private String city;
    private String state;
    private String pincode;
}
