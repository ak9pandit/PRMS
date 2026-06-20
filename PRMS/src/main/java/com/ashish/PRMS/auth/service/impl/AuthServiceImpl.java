package com.ashish.PRMS.auth.service.impl;

import com.ashish.PRMS.auth.dto.LoginRequest;
import com.ashish.PRMS.auth.dto.LoginResponse;
import com.ashish.PRMS.auth.dto.SetupOwnerRequest;
import com.ashish.PRMS.auth.entity.User;
import com.ashish.PRMS.auth.repository.UserRepository;
import com.ashish.PRMS.auth.service.AuthService;
import com.ashish.PRMS.enums.Role;
import com.ashish.PRMS.enums.Status;
import com.ashish.PRMS.exception.BusinessException;
import com.ashish.PRMS.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtService jwtService;;

    @Override
    public String setupOwner(SetupOwnerRequest setupOwnerRequest) {

        User user1 = new User();
        String email = setupOwnerRequest.getEmail();
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()){
            throw new BusinessException("Owner already exists");
        }else {
            user1.setName(setupOwnerRequest.getName());
            user1.setEmail(setupOwnerRequest.getEmail());
            user1.setPassword(passwordEncoder.encode(setupOwnerRequest.getPassword()));
            user1.setPhone(setupOwnerRequest.getPhone());
            user1.setRole(Role.OWNER);
            user1.setStatus(Status.ACTIVE);
            user1.setCreatedAt(LocalDateTime.now());
            user1.setUpdatedAt(LocalDateTime.now());
        }
        userRepository.save(user1);
        return "Owner created";
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new BusinessException("Invalid email"));
        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
            throw new BusinessException("Invalid password");
        }

        String generateToken = jwtService.generateToken(user);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setAccessToken(generateToken);
        loginResponse.setTokenType("Bearer");

        return loginResponse;
    }

    @Override
    public Boolean isOwnerCreated() {
        return userRepository.existsByRole(Role.OWNER);
    }
}
