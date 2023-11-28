package com.example.travelback.user.dto;

import lombok.Data;

@Data
public class Auth {
    private Integer id; // pk
    private String memberId; // fk
    private String name;
}
