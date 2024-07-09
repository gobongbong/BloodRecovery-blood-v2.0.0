package com.potatoes.bloodrecovery.application.commandservices;

import com.potatoes.bloodrecovery.domain.model.aggregates.DonationHistory;
import com.potatoes.bloodrecovery.domain.repository.BloodRequestRepository;
import com.potatoes.bloodrecovery.domain.repository.DonationHistoryRepository;
import com.potatoes.bloodrecovery.domain.repository.UserRepository;
import com.potatoes.bloodrecovery.interfaces.rest.dto.CompleteDirectedDonationReqDto;
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

import static com.potatoes.bloodrecovery.mock.MockDataUtil.commonBloodRequest;
import static com.potatoes.bloodrecovery.mock.MockDataUtil.commonDonationHistoryList_Card;
import static com.potatoes.constants.RequestStatus.REGISTER;
import static com.potatoes.constants.ResponseCode.FAIL_COMPLETE_BLOOD_REQUEST;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CompleteDirectedDonationCommandServiceTest {

    @InjectMocks
    CompleteDirectedDonationCommandService completeDirectedDonationCommandService;

    @Mock
    DonationHistoryRepository donationHistoryRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    BloodRequestRepository bloodRequestRepository;

    @Test
    @DisplayName("지정 헌혈 완료처리에 성공한다.")
    void completeDirectedDonation_success(){
        //given
        List<String> cidList = new ArrayList<>();
        cidList.add("111");
        cidList.add("222");

        CompleteDirectedDonationReqDto completeDirectedDonationReqDto = CompleteDirectedDonationReqDto.builder()
                .cidList(cidList)
                .build();

        given(donationHistoryRepository.findByCidAndDonationTypeAndDonationStatus(any(), any(), any())).willReturn(commonDonationHistoryList_Card());
        given(bloodRequestRepository.findByRequestId(any())).willReturn(Optional.ofNullable(commonBloodRequest(REGISTER)));

        //when, then
        assertDoesNotThrow(() -> completeDirectedDonationCommandService.completeDirectedDonation(111L, completeDirectedDonationReqDto));
    }

    @Test
    @DisplayName("지정 헌혈 완료처리에 실패한다. (헌혈 이력 미존재)")
    void completeDirectedDonation_fail_no_history(){
        //given
        List<String> cidList = new ArrayList<>();
        cidList.add("111");
        cidList.add("222");

        CompleteDirectedDonationReqDto completeDirectedDonationReqDto = CompleteDirectedDonationReqDto.builder()
                .cidList(cidList)
                .build();

        List<DonationHistory> list = new ArrayList<>();

        given(donationHistoryRepository.findByCidAndDonationTypeAndDonationStatus(any(), any(), any())).willReturn(list);

        //when
        Throwable throwable = assertThrows(ApiException.class, () -> completeDirectedDonationCommandService.completeDirectedDonation(111L, completeDirectedDonationReqDto));

        //then
        assertEquals(throwable.getMessage(), FAIL_COMPLETE_BLOOD_REQUEST.getMessage());
    }

    @Test
    @DisplayName("지정 헌혈 완료처리에 실패한다. (헌혈 요청글 미존재)")
    void completeDirectedDonation_fail_no_request(){
        //given
        List<String> cidList = new ArrayList<>();
        cidList.add("111");
        cidList.add("222");

        CompleteDirectedDonationReqDto completeDirectedDonationReqDto = CompleteDirectedDonationReqDto.builder()
                .cidList(cidList)
                .build();

        given(bloodRequestRepository.findByRequestId(any())).willThrow();

        //when
        Throwable throwable = assertThrows(ApiException.class, () -> completeDirectedDonationCommandService.completeDirectedDonation(111L, completeDirectedDonationReqDto));

        //then
        assertEquals(throwable.getMessage(), FAIL_COMPLETE_BLOOD_REQUEST.getMessage());
    }
}
