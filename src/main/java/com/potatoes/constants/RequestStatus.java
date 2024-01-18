package com.potatoes.constants;

import lombok.Getter;

@Getter
public enum RequestStatus {

    REGISTER("등록"),
    DIRECTED_DONATION_ONGOING("지정헌혈 대기 중"),
    ONGOING("진행 중"),
    COMPLETE("완료"),
    DELETE("삭제");

    private String status;

    RequestStatus(String status) {
        this.status = status;
    }
}