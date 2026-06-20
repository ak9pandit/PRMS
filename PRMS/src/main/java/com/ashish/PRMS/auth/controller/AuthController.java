package com.ashish.PRMS.auth.controller;

import com.ashish.PRMS.auth.dto.LoginRequest;
import com.ashish.PRMS.auth.dto.LoginResponse;
import com.ashish.PRMS.auth.dto.SetupOwnerRequest;
import com.ashish.PRMS.auth.dto.SetupStatusResponse;
import com.ashish.PRMS.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/setup-owner")
    public ResponseEntity<String> setupOwner(@RequestBody SetupOwnerRequest setupOwnerRequest){
        String msg = authService.setupOwner(setupOwnerRequest);
        return ResponseEntity.ok(msg);
    }

    @GetMapping("/setup-status")
    public ResponseEntity<SetupStatusResponse> getSetupOwnerStatus(){
        Boolean ownerCreated = authService.isOwnerCreated();
        SetupStatusResponse setupStatusResponse = new SetupStatusResponse(ownerCreated);
        return new ResponseEntity<>(setupStatusResponse, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        LoginResponse loginResponse = authService.login(loginRequest);
        return ResponseEntity.ok(loginResponse);
    }
}
