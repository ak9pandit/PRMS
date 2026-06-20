package com.ashish.PRMS.auth.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {

    String accessToken;
    String tokenType;
}
