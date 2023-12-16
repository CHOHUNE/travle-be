package com.example.travelback.trans.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Trans {
    private Integer tId;
    private LocalDateTime transStartDay;
    private String transTitle;
    private String transPrice;
    private String transInsertPrice;
    private String transContent;
    private String transStartLocation;
    private String transArriveLocation;
    private String transAddress;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime transStartDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime transEndDate;
    private LocalDateTime transInserted;
    private String typeName;
    private String url;

    // 이미지 조회 테스트 중
    private TransMainImage mainImage;

    private List<TransContentImages> contentImages;

}
