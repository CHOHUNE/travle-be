package com.example.travelback.hotelPage.domain;


import lombok.Data;

@Data
public class Like {
    private Integer id;
    private Integer hotelId;
    private String userId;
    private String name;
    private String mainImgUrl;
//    private String mainImg;
    private String location;

}
