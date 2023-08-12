package com.potatoes.bloodrecovery.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
@Getter
@Builder
@ToString
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    //todo 여기에 그냥 회원 아 안되겠구먼.. 등급이 바뀔수도 있으니까... 닉네임도.. 그럼 매번.. 찔러야하나..?

    private String postId;
    private String title;
    private String contents;
    private LocalDateTime regDate;
    private String postStatus; //todo 따로 만들자
}
