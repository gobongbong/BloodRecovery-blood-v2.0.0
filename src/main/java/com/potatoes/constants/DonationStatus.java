package com.potatoes.constants;

import com.google.common.collect.ImmutableMap;
import lombok.Getter;

import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum DonationStatus {

    DIRECTED_DONATION_ONGOING("지정헌혈 대기 중"),
    COMPLETE("완료");

    private final String value;

    DonationStatus(String value) {
        this.value = value;
    }

    private static final ImmutableMap<String, DonationStatus> codes = ImmutableMap.copyOf(
            Stream.of(values()).collect(Collectors.toMap(DonationStatus::getValue, Function.identity())));

    public static DonationStatus find(final String value) {
        return codes.get(value);
    }

}
