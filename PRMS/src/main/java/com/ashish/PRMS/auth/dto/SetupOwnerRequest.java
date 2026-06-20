package com.ashish.PRMS.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SetupOwnerRequest {

    private String name;
    private String email;
    private String password;
    private String phone;

}
