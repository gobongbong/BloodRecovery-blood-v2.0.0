package com.potatoes.bloodrecovery.domain.model.valueobjects;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Slf4j
@Getter
@Builder
@ToString
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    private String postId;
    private String title;
    private String contents;
    private LocalDateTime regDate; //todo 글 생성될 때마다 자동으로 들어가게 수정
    private String postStatus; //todo enum으로 따로 만들자
}
