package com.potatoes.bloodrecovery.domain.model.view;

import lombok.*;
import org.springframework.http.ResponseEntity;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerInfoView {
    /**
     * 회원 등급
     */
    private String gradeSn;

    /**
     * 회원 닉네임
     */
    private String userNickname;

    /**
     * 회원 아이디
     */
    private String userId;


    /**
     * 회원 혈액형
     */
    private String userBlood;
}
