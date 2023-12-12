package com.example.travelback.hotelPage.domain;


import lombok.Data;

import java.util.List;

@Data
public class HotelRoomType {


    private double originalPriceWeekday;
    private double salePriceWeekday;
    private double originalPriceWeekend;
    private double salePriceWeekend;
    private String roomtype;
//    private String roomImg;
    private long hid;

}

