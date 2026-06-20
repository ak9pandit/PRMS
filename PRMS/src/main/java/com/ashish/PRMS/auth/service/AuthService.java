package com.ashish.PRMS.auth.service;

import com.ashish.PRMS.auth.dto.LoginRequest;
import com.ashish.PRMS.auth.dto.LoginResponse;
import com.ashish.PRMS.auth.dto.SetupOwnerRequest;

public interface AuthService {

    String setupOwner(SetupOwnerRequest setupOwnerRequest);

    LoginResponse login(LoginRequest loginRequest);

    Boolean isOwnerCreated();

}
