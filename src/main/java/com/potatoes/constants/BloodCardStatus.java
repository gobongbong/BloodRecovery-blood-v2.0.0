package com.potatoes.constants;

import lombok.Getter;

@Getter
public enum BloodCardStatus {

    REGISTER("등록"),
    DELETE("삭제");

    private String status;

    BloodCardStatus(String status) {
        this.status = status;
    }
}
