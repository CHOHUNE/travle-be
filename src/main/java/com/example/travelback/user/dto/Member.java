package com.example.travelback.user.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Member {
    private Integer id; // pk
    private String userId;
    private String userPassword;
    private String userName;
    private String userAddress1;
    private String userAddress2;
    private String userAddress3;
    private String userPhoneNumber;
    private String userEmail;
    private LocalDateTime inserted;
    private List<Auth> auth;
}
