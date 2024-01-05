package com.potatoes.constants;

import lombok.Getter;

@Getter
public enum PostStatus {

    REGISTER("등록"),
    COMPLETE("완료"),
    DELETE("삭제");

    private String status;

    PostStatus(String status) {
        this.status = status;
    }
}
