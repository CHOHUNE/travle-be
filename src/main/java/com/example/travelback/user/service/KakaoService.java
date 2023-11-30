package com.example.travelback.user.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;

@Service
public class KakaoService {

    @Value("${Rest.api.key}")
    private String RestApiKey;

    public String getKaKaoAccessToken(String code) {
        String access_Token = "";
        String refresh_Token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try{
            URL url = new URL(reqURL);
        } catch (IOException e) {

        }
        return access_Token;
    }

}
