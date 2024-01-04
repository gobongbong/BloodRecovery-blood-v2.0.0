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
}
