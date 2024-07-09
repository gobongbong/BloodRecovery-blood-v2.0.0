package com.potatoes.bloodrecovery.application.queryservices;

import com.potatoes.bloodrecovery.domain.repository.DonationHistoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.potatoes.bloodrecovery.mock.MockDataUtil.commonDonationHistoryList_Card;
import static com.potatoes.constants.ResponseCode.FAIL_GET_DONATION_HISTORY;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class GetDonationHistoryQueryServiceTest {

    @InjectMocks
    GetDonationHistoryQueryService donationHistoryQueryService;

    @Mock
    DonationHistoryRepository donationHistoryRepository;

    @Test
    @DisplayName("헌혈 이력 조회에 성공한다.")
    void getDonationHistory_success(){
        //given
        given(donationHistoryRepository.findByCid(any())).willReturn(commonDonationHistoryList_Card());

        //when, then
        assertDoesNotThrow(() -> donationHistoryQueryService.getDonationHistory("111"));
    }

    @Test
    @DisplayName("헌혈 이력 조회에 실패한다.")
    void getDonationHistory_fail(){
        //given
        given(donationHistoryRepository.findByCid(any())).willThrow();

        //when
        Throwable throwable = assertThrows(Exception.class, ()-> donationHistoryQueryService.getDonationHistory("111"));

        //then
        assertEquals(throwable.getMessage(), FAIL_GET_DONATION_HISTORY.getMessage());
    }
}
