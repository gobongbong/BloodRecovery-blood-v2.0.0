package com.potatoes.bloodrecovery.application.commandservices;

import com.potatoes.bloodrecovery.domain.model.aggregates.BloodRequest;
import com.potatoes.bloodrecovery.domain.model.aggregates.DonationHistory;
import com.potatoes.bloodrecovery.domain.model.commands.DirectedBloodDonationCommand;
import com.potatoes.bloodrecovery.domain.model.valueobjects.Post;
import com.potatoes.bloodrecovery.domain.repository.BloodRequestRepository;
import com.potatoes.bloodrecovery.domain.repository.DonationHistoryRepository;
import com.potatoes.bloodrecovery.domain.repository.UserRepository;
import com.potatoes.constants.RequestStatus;
import com.potatoes.exception.ApiException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.potatoes.constants.StaticValues.BLOOD_CARD_DONATION;
import static com.potatoes.constants.StaticValues.DIRECTED_DONATION;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

        BloodRequest bloodRequest = BloodRequest.builder()
                .requestId(1L)
                .bloodDonationCnt(0)
                .bloodReqCnt(7)
                .requestStatus(RequestStatus.REGISTER)
                .requestType(DIRECTED_DONATION)
                .cid("1111")
                .post(new Post())
                .build();

        given(bloodRequestRepository.findByRequestIdAndRequestStatusIn(any(), any())).willReturn(Optional.of(bloodRequest));

        // when, then
        assertDoesNotThrow(() ->
                directedBloodDonationCommandService.applyDirectedBloodDonation(directedBloodDonationCommand));
    }

    @Test
    @DisplayName("잘못된 requestType 으로 인해 지정헌혈 신청에 실패한다.")
    void applyDirectedBloodDonation_type_error() {
        // given
        DirectedBloodDonationCommand directedBloodDonationCommand = DirectedBloodDonationCommand.builder()
                .cid("2222")
                .requestId(1L)
                .build();

        BloodRequest bloodRequest = BloodRequest.builder()
                .requestId(1L)
                .bloodDonationCnt(0)
                .bloodReqCnt(7)
                .requestType(BLOOD_CARD_DONATION)
                .cid("1111")
                .post(new Post())
                .build();

        given(bloodRequestRepository.findByRequestIdAndRequestStatusIn(any(), any())).willReturn(Optional.of(bloodRequest));

        // when, then
        assertThrows(ApiException.class, () ->
                directedBloodDonationCommandService.applyDirectedBloodDonation(directedBloodDonationCommand));
    }

    @Test
    @DisplayName("진행 중인 지정 헌혈 이력이 있어 지정헌혈 신청에 실패한다.")
    void applyDirectedBloodDonation_cid_error() {
        // given
        DirectedBloodDonationCommand directedBloodDonationCommand = DirectedBloodDonationCommand.builder()
                .cid("2222")
                .requestId(1L)
                .build();

        BloodRequest bloodRequest = BloodRequest.builder()
                .requestId(1L)
                .bloodDonationCnt(0)
                .bloodReqCnt(7)
                .requestType(DIRECTED_DONATION)
                .cid("1111")
                .post(new Post())
                .build();

        given(bloodRequestRepository.findByRequestIdAndRequestStatusIn(any(), any())).willReturn(Optional.of(bloodRequest));

        List<DonationHistory> donationHistories = new ArrayList<>();
        donationHistories.add(
                DonationHistory.builder()
                        .cid("2222")
                        .requestId(1l)
                        .donationStatus(RequestStatus.DIRECTED_DONATION_ONGOING)
                        .donationCnt(1)
                        .historyId(1l)
                        .donationType(DIRECTED_DONATION)
                        .date(LocalDateTime.now())
                        .build()
        );

        given(donationHistoryRepository.findByCidAndDonationTypeAndDonationStatus(any(), any(), any())).willReturn(donationHistories);

        // when, then
        assertThrows(ApiException.class, () ->
                directedBloodDonationCommandService.applyDirectedBloodDonation(directedBloodDonationCommand));
    }
}