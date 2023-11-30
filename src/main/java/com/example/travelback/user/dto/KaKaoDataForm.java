package com.example.travelback.user.dto;

import lombok.Data;

@Data
public class KaKaoDataForm {
    private Long id;
    private String nickname;
    private String profileImage;

    public KaKaoDataForm(Long id, String nickname, String profileImage) {
        this.id = id;
        this.nickname = nickname;
        this.profileImage = profileImage;
    }
}
