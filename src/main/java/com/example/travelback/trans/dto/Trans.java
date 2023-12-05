package com.example.travelback.trans.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Trans {
    private Integer tId;
    private LocalDateTime transStartDay;
    private String transTitle;
    private String transPrice;
    private String transContent;
    private LocalDateTime transInserted;
    private String typeName;
    private String url;

    // 이미지 조회 테스트 중
    private TransMainImage mainImage;

    private List<TransContentImages> contentImages;

}
