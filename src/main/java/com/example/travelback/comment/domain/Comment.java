package com.example.travelback.comment.domain;

import lombok.Data;

@Data
public class Comment {
    private Integer id;
    private  Integer boardId;
    private  String userId;
    private  String comment;
    private String inserted;

}
