package com.potatoes.constants;

import com.google.common.collect.ImmutableMap;
import lombok.Getter;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum RequestStatus {

    REGISTER("등록"),
    ONGOING("진행 중"),
    COMPLETE("완료"),
    DELETE("삭제");

    private final String value;

    RequestStatus(String value) {
        this.value = value;
    }

    private static final ImmutableMap<String, RequestStatus> codes = ImmutableMap.copyOf(
            Stream.of(values()).collect(Collectors.toMap(RequestStatus::getValue, Function.identity())));

    public static RequestStatus find(final String value) {
        return codes.get(value);
    }

    public static List<RequestStatus> getOngoing(){
        return List.of(REGISTER, ONGOING);
    }
}
