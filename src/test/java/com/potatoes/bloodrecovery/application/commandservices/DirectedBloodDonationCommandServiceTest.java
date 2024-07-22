package com.potatoes.bloodrecovery.application.commandservices;

import com.potatoes.bloodrecovery.domain.model.commands.DirectedBloodDonationCommand;
import com.potatoes.bloodrecovery.domain.repository.BloodRequestRepository;
import com.potatoes.bloodrecovery.domain.repository.DonationHistoryRepository;
import com.potatoes.bloodrecovery.exception.ApiException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.potatoes.bloodrecovery.mock.MockDataUtil.commonBloodRequest;
import static com.potatoes.constants.RequestStatus.REGISTER;
import static com.potatoes.constants.ResponseCode.EXIST_ONGOING_DIRECTED_BLOOD_DONATION_HISTORY;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class DirectedBloodDonationCommandServiceTest {
    @InjectMocks
    DirectedBloodDonationCommandService directedBloodDonationCommandService;

    @Mock
    BloodRequestRepository bloodRequestRepository;

    @Mock
    DonationHistoryRepository donationHistoryRepository;

    @Test
    @DisplayName("지정헌혈 신청에 성공한다.")
    void applyDirectedBloodDonation_success() {
        // given
        DirectedBloodDonationCommand directedBloodDonationCommand = DirectedBloodDonationCommand.builder()
                .cid("2222")
                .requestId(1L)
                .build();

        given(bloodRequestRepository.findByRequestIdAndRequestStatusIn(any(), any())).willReturn(Optional.of(commonBloodRequest(REGISTER)));

        // when, then
        assertDoesNotThrow(() ->
                directedBloodDonationCommandService.applyDirectedBloodDonation(directedBloodDonationCommand));
    }

    @Test
    @DisplayName("지정 헌혈 신청에 실패한다. (진행 중인 지정 헌혈 이력이 있는 경우)")
    void applyDirectedBloodDonation_cid_error() {
        // given
        DirectedBloodDonationCommand directedBloodDonationCommand = DirectedBloodDonationCommand.builder()
                .cid("2222")
                .requestId(1L)
                .build();

        given(bloodRequestRepository.findByRequestIdAndRequestStatusIn(any(), any())).willReturn(Optional.of(commonBloodRequest(REGISTER)));
        given(donationHistoryRepository.existsByCidAndDonationTypeAndDonationStatus(any(), any(), any())).willReturn(true);

        // when
        Throwable throwable = assertThrows(ApiException.class, () ->
                directedBloodDonationCommandService.applyDirectedBloodDonation(directedBloodDonationCommand));

        // then
        assertEquals(throwable.getMessage(), EXIST_ONGOING_DIRECTED_BLOOD_DONATION_HISTORY.getMessage());
    }
}