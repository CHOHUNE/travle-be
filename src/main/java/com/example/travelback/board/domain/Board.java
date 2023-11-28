package com.example.travelback.board.domain;

import lombok.Data;

@Data
public class Board {
    private  Integer id;
    private  String title;
    private  String content;
    private  String writer;
    private  String inserted;
}
