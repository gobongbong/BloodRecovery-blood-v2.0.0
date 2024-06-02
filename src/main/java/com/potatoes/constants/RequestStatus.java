package com.potatoes.constants;

import lombok.Getter;

import java.util.List;

@Getter
public enum RequestStatus {

    REGISTER("등록"),
    DIRECTED_DONATION_ONGOING("지정헌혈 대기 중"),
    ONGOING("진행 중"),
    COMPLETE("완료"),
    DELETE("삭제");

    private final String value;

    RequestStatus(String value) {
        this.value = value;
    }

    public static List<RequestStatus> getOngoing(){
        return List.of(REGISTER, DIRECTED_DONATION_ONGOING, ONGOING);
    }
}
