//package com.example.travelback.user.service;
//
//import com.example.travelback.user.dto.KaKaoDataForm;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonParser;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import java.io.*;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//
//@Service
//public class KakaoService {
//
//    @Value("${Rest.api.key}")
//    private String RestApiKey;
//
//    @Value("${Redirect.uri}")
//    private String redirectUri;
//
//    public String getAccessToken(String code) {
//        String access_Token = null;
//        String refresh_Token = null;
//        String reqURL = "https://kauth.kakao.com/oauth/token";
//
//        try{
//            URL url = new URL(reqURL);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//
//            // POST 요청을 위해 기본값이 false인 setDoOutPut을 true로
//            conn.setRequestMethod("POST");
//            conn.setDoOutput(true);
//
//            // POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
//            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
//            StringBuilder sb = new StringBuilder();
//            sb.append("grant_type=authorization_code");
//            sb.append("&client_id=").append(RestApiKey);
//            sb.append("&redirect_uri=").append(redirectUri);
//            sb.append("&code=").append(code);
//            bw.write(sb.toString());
//            bw.flush();
//
//            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            String line;
//            String result = "";
//
//            while ((line = br.readLine()) != null) {
//                result += line;
//            }
//
//            JsonParser parser = new JsonParser();
//            JsonElement element = parser.parse(result);
//
//            access_Token = element.getAsJsonObject().get("access_token").getAsString();
//            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();
//
//            br.close();
//            bw.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return access_Token;
//    }
//
//    public KaKaoDataForm createKakaoUser(String token) {
//        String reqURL = "https://kapi.kakao.com/v2/user/me";
//
//        try {
//            URL url = new URL(reqURL);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//
//            conn.setRequestMethod("POST");
//            conn.setDoOutput(true);
//            conn.setRequestProperty("Authorization", "Bearer " + token);
//
//            int responseCode = conn.getResponseCode();
//            System.out.println("responseCode = " + responseCode);
//
//            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//
//            String line;
//            String result = "";
//
//            while((line = br.readLine()) != null) {
//                result += line;
//            }
//
//            JsonParser parser = new JsonParser();
//            JsonElement element = parser.parse(result);
//
//            Long id = element.getAsJsonObject().get("id").getAsLong();
//
//
//            boolean hasEmail = false;
//            JsonElement kakaoAccountElement = element.getAsJsonObject().get("kakao_account");
//            if (kakaoAccountElement != null && kakaoAccountElement.isJsonObject()) {
//                JsonElement hasEmailElement = kakaoAccountElement.getAsJsonObject().get("has_email");
//                if (hasEmailElement != null && hasEmailElement.isJsonPrimitive()) {
//                    hasEmail = hasEmailElement.getAsBoolean();
//                }
//            }
//            String email = "";
//            if (hasEmail) {
//                JsonElement emailElement = kakaoAccountElement.getAsJsonObject().get("email");
//                if (emailElement != null && emailElement.isJsonPrimitive()) {
//                    email = emailElement.getAsString();
//                }
//            }
//
//            String nickname = element.getAsJsonObject().get("properties").getAsJsonObject().get("nickname").getAsString();
//            String profile_image = element.getAsJsonObject().get("properties").getAsJsonObject().get("profile_image").getAsString();
//
//            System.out.println("id: " + id);
//            System.out.println("email: " + email);
//            System.out.println("nickname: " + nickname);
//            System.out.println("profile_image: " + profile_image);
//
//            br.close();
//
//            KaKaoDataForm res = new KaKaoDataForm(id, email, nickname, profile_image);
//            return res;
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//}
