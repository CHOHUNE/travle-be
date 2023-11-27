package com.example.travelback.dto;

import lombok.Data;

import java.time.LocalDateTime;

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
}
