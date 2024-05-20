package com.potatoes.bloodrecovery.application.commandservices;

import com.potatoes.bloodrecovery.domain.model.aggregates.BloodRequest;
import com.potatoes.bloodrecovery.domain.model.commands.DirectedBloodDonationCommand;
import com.potatoes.bloodrecovery.domain.model.valueobjects.DirectedDonation;
import com.potatoes.bloodrecovery.domain.model.valueobjects.Post;
import com.potatoes.bloodrecovery.domain.model.view.UserInfoView;
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

import java.util.Optional;

import static com.potatoes.bloodrecovery.interfaces.rest.constants.apiurl.BloodApiUrl.DONATION_BLOOD_CARD;
import static com.potatoes.constants.ResponseCode.NO_DATA;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class DirectedBloodDonationCommandServiceTest {
    @InjectMocks
    DirectedBloodDonationCommandService directedBloodDonationCommandService;

    @Mock
    UserRepository userRepository;

    @Mock
    BloodRequestRepository bloodRequestRepository;

    @Mock
    DonationHistoryRepository donationHistoryRepository;

    @Test
    @DisplayName("지정헌혈 신청에 성공한다.")
    void applyDirectedBloodDonation_success() {
        // given
        BloodRequest bloodRequest = new BloodRequest(1L, "2222", RequestStatus.REGISTER, "지정헌혈", 2, 0, new Post(), new DirectedDonation(), false);
        given(bloodRequestRepository.findByRequestId(anyLong())).willReturn(Optional.of(bloodRequest));

        DirectedBloodDonationCommand directedBloodDonationCommand = new DirectedBloodDonationCommand("1111", 1L);

        // when, then
        assertDoesNotThrow(() -> {
            directedBloodDonationCommandService.applyDirectedBloodDonation(directedBloodDonationCommand);
        });
    }

    @Test
    @DisplayName("잘못된 cid로 인해 지정헌혈 신청에 실패한다.")
    void applyDirectedBloodDonation_cid_error() {
        // given
        String cid = "2222";

        UserInfoView userInfoView = UserInfoView.builder()
                .userId("1111")
                .nickname("고봉이")
                .name("최고봉")
                .phone("01099999999")
                .fileNm(".....img")
                .email("gobong@naver.com")
                .build();

        BloodRequest bloodRequest = new BloodRequest(1L, "1111", RequestStatus.REGISTER, DONATION_BLOOD_CARD, 2, 0, new Post(), new DirectedDonation(), false);

        given(userRepository.getUserInfo(anyString())).willReturn(userInfoView);
        given(bloodRequestRepository.findByRequestId(anyLong())).willReturn(Optional.of(bloodRequest));

        DirectedBloodDonationCommand directedBloodDonationCommand = new DirectedBloodDonationCommand(cid, 1L);

        // when, then
        assertThrows(ApiException.class, () -> {
            directedBloodDonationCommandService.applyDirectedBloodDonation(directedBloodDonationCommand);
        });
    }

    @Test
    @DisplayName("requestType 오류로 인해 지정헌혈 신청에 실패한다.")
    void applyDirectedBloodDonation_type_error() {
        // given
        BloodRequest bloodRequest = new BloodRequest(1L, "2222", RequestStatus.REGISTER, DONATION_BLOOD_CARD, 2, 0, new Post(), new DirectedDonation(), false);
        given(bloodRequestRepository.findByRequestId(anyLong())).willReturn(Optional.of(bloodRequest));

        DirectedBloodDonationCommand directedBloodDonationCommand = new DirectedBloodDonationCommand("1111", 1L);

        // when, then
        assertThrows(ApiException.class, () -> {
            directedBloodDonationCommandService.applyDirectedBloodDonation(directedBloodDonationCommand);
        });
    }
}