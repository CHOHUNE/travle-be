package com.example.travelback.trans.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransLike {
    private Integer id;
    private Integer transId;
    private String userId;
    private LocalDateTime createdAt;
}
