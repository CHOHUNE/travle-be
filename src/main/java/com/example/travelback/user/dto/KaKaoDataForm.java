package com.example.travelback.user.dto;

import lombok.Data;

@Data
public class KaKaoDataForm {
    private Long id;
    private String nickname;
    private String email;
    private String profileImage;

    public void KakaoDataForm(Long id, String nickname, String email, String profileImage) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.profileImage = profileImage;
    }
}
