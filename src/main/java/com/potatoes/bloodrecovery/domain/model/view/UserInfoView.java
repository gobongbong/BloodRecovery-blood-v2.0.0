package com.potatoes.bloodrecovery.domain.model.view;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserInfoView {

    private String userId;
    private String nickname;
    private String name;
    private String phone;
    private String fileNm;
    private String email;

    public static UserInfoView userInfoView(){
        return UserInfoView.builder()
                .userId("1111")
                .nickname("gobong")
                .name("고봉")
                .phone("010-1111-2222")
                .fileNm("aaaaa")
                .email("gobong@naver.com")
                .build();
    }
}
