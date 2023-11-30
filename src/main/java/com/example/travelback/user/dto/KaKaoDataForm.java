package com.example.travelback.user.dto;

import lombok.Data;

@Data
public class KaKaoDataForm {
    private Long id;
    private String email;
    private String nickname;
    private String profileImage;

    public KaKaoDataForm(Long id, String email, String nickname, String profileImage) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.profileImage = profileImage;
    }
}
