package com.example.travelback.trans.dto;

import lombok.Data;

@Data
public class TransLike {
    private Integer id;
    private Integer transId;
    private String userId;
}
