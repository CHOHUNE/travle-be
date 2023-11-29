package com.example.travelback.trans.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Trans {
    private Integer tId;
    private LocalDateTime transStartDay;
    private String transTitle;
    private String transPrice;
    private String transContent;
    private LocalDateTime transInserted;

}
