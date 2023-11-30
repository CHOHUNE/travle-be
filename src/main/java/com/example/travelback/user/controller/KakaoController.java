//package com.example.travelback.user.controller;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.client.RestTemplate;
//
//@Controller
//@RequestMapping("/oauth2/kakao")
//public class KakaoController {
//
//    @Value("${Rest.api.key}")
//    private String RestApiKey;
//
//    @Value("${Redirect.uri}")
//    private String redirectUri;
//
//    @GetMapping
//    public String getAccessToken(@RequestParam("code") String code) {
//        System.out.println("code = " + code);
//
//        HttpHeaders httpHeaders = new HttpHeaders();
//
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        params.add("grant_type", "authorization_code");
//        params.add("client_id", RestApiKey);
//        params.add("redirect_uri", redirectUri);
//        params.add("code", code);
//
//        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(params, httpHeaders);
//
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<Object> response = restTemplate.exchange(
//                "http://kauth.kakao.com/oauth/token",
//                HttpMethod.POST,
//                httpEntity,
//                Object.class
//        );
//        System.out.println("response = " + response);
//        return "home";
//    }
//}
