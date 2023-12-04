package com.example.travelback.user.dto;

import com.example.travelback.user.util.AppUtill;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Data
public class Member {
    private Integer id; // pk

    // ---------- user 관련 ----------
    private String userId;
    private String userPassword;
    private String userName;
    private String userPostCode;
    private String userAddress;
    private String userDetailAddress;
    private String userPhoneNumber;
    private String userEmail;
    private LocalDateTime inserted;

    // --------- 관리자 관련 ----------
    private List<Auth> auth;

    // ---------- sms 관련 ----------
    private String verificationCode;
    private String sendSMS;

    // ---------- 시간 관련 ----------
    public String getAgo() {
       return AppUtill.getAgo(inserted, LocalDateTime.now());

    }
}
