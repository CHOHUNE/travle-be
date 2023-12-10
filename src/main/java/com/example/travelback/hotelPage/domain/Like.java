package com.example.travelback.hotelPage.domain;


import com.example.travelback.user.util.AppUtill;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class Like {
    private Integer id;
    private Integer hotelId;
    private String userId;
    private String name;
    private String mainImgUrl;
    private String location;
    private String roomType;
    private LocalDateTime createdAt;

    // ---------- 시간 관련 ----------
    public String getAgo() {
        return AppUtill.getAgo(createdAt, LocalDateTime.now());

    }
}


