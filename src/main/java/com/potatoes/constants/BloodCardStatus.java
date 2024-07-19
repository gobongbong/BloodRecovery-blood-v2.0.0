package com.potatoes.constants;

import com.google.common.collect.ImmutableMap;
import lombok.Getter;

import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum BloodCardStatus {

    REGISTER("등록"),
    DELETE("삭제"),
    OWNER_CHANGE("소유자 변경");

    private final String value;

    BloodCardStatus(String status) {
        this.value = status;
    }

    private static final ImmutableMap<String, BloodCardStatus> codes = ImmutableMap.copyOf(
            Stream.of(values()).collect(Collectors.toMap(BloodCardStatus::getValue, Function.identity())));

    public static BloodCardStatus find(final String value) {
        return codes.get(value);
    }
}
