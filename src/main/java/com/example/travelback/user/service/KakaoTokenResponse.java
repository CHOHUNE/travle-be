package com.example.travelback.user.service;

import lombok.Data;

@Data
public class KakaoTokenResponse {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private long expires_in;
    private String scope;

}
