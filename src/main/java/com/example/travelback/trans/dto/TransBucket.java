package com.example.travelback.trans.dto;

import com.example.travelback.user.util.AppUtill;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransBucket {
    public String tId;
    public String userId;
    public String transTitle;
    public String typeName;
    public String url;
    public String transStartLocation;
    public String transArriveLocation;
    public String transAddress;
    public LocalDateTime createdAt;

    public String getAgo() {
        return AppUtill.getAgo(createdAt, LocalDateTime.now());

    }
}
