package com.example.travelback.trans.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransBucket {
    public String userId;
    public String transTitle;
    public String typeName;
    public String url;
    public String transStartLocation;
    public String transArriveLocation;
    public String transAddress;
    public LocalDateTime createdAt;
}
