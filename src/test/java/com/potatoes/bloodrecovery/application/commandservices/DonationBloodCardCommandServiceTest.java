package com.potatoes.bloodrecovery.application.commandservices;

import com.potatoes.bloodrecovery.domain.model.aggregates.BloodCard;
import com.potatoes.bloodrecovery.domain.model.commands.DonationBloodCardCommand;
import com.potatoes.bloodrecovery.domain.repository.*;
import com.potatoes.exception.ApiException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.potatoes.bloodrecovery.mock.MockDataUtil.commonBloodCardList;
import static com.potatoes.bloodrecovery.mock.MockDataUtil.commonBloodRequest_Register;
import static com.potatoes.constants.ResponseCode.NO_BLOOD_CARD;
import static com.potatoes.constants.ResponseCode.NO_BLOOD_REQUEST;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class DonationBloodCardCommandServiceTest {

    @InjectMocks
    DonationBloodCardCommandService donationBloodCardCommandService;

    @Mock
    UserRepository userRepository;
    @Mock
    BloodRequestRepository bloodRequestRepository;
    @Mock
    BloodCardRepository bloodCardRepository;
    @Mock
    BloodCardHistoryRepository bloodCardHistoryRepository;
    @Mock
    DonationHistoryRepository donationHistoryRepository;

    @Test
    @DisplayName("헌혈증 기부에 성공한다.")
    void donationBloodCard_success(){
        //given
        DonationBloodCardCommand donationBloodCardCommand = DonationBloodCardCommand.builder()
                .cardCnt(2)
                .cid("111")
                .requestId(111L)
                .build();

        given(bloodRequestRepository.findByRequestIdAndRequestStatusIn(any(), any())).willReturn(Optional.ofNullable(commonBloodRequest_Register()));
        given(bloodCardRepository.findByCid(any())).willReturn(commonBloodCardList());

        //when, then
        assertDoesNotThrow(() -> donationBloodCardCommandService.donationBloodCard(donationBloodCardCommand));
    }

    @Test
    @DisplayName("헌혈증 기부에 실패한다. (진행 상태인 요청글 미존재)")
    void donationBloodCard_fail(){
        //given
        DonationBloodCardCommand donationBloodCardCommand = DonationBloodCardCommand.builder()
                .cardCnt(2)
                .cid("111")
                .requestId(111L)
                .build();

        given(bloodRequestRepository.findByRequestIdAndRequestStatusIn(any(), any())).willThrow(new ApiException(NO_BLOOD_REQUEST));

        //when
        Throwable throwable = assertThrows(ApiException.class, () -> donationBloodCardCommandService.donationBloodCard(donationBloodCardCommand));

        //then
        assertEquals(throwable.getMessage(), NO_BLOOD_REQUEST.getMessage());
    }

    @Test
    @DisplayName("헌혈증 기부에 실패한다. (헌혈증 미존재)")
    void donationBloodCard_fail_no_card(){
        //given
        DonationBloodCardCommand donationBloodCardCommand = DonationBloodCardCommand.builder()
                .cardCnt(2)
                .cid("111")
                .requestId(111L)
                .build();
        List<BloodCard> cards = new ArrayList<>();

        given(bloodRequestRepository.findByRequestIdAndRequestStatusIn(any(), any())).willReturn(Optional.ofNullable(commonBloodRequest_Register()));
        given(bloodCardRepository.findByCid(any())).willReturn(cards);

        //when
        Throwable throwable = assertThrows(ApiException.class, () -> donationBloodCardCommandService.donationBloodCard(donationBloodCardCommand));

        //then
        assertEquals(throwable.getMessage(), NO_BLOOD_CARD.getMessage());
    }
}
