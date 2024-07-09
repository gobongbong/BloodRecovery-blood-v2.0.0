package com.potatoes.bloodrecovery.application.queryservices;

import com.potatoes.bloodrecovery.domain.model.view.DirectedDonationApplicantView;
import com.potatoes.bloodrecovery.domain.repository.BloodRequestRepository;
import com.potatoes.bloodrecovery.domain.repository.DonationHistoryRepository;
import com.potatoes.bloodrecovery.domain.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static com.potatoes.bloodrecovery.mock.MockDataUtil.commonDonationHistoryList_Directed;
import static com.potatoes.bloodrecovery.mock.MockDataUtil.commonUserInfoView;
import static com.potatoes.constants.ResponseCode.FAIL_GET_DIRECTED_DONATION_APPLICANT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class GetDirectedDonationApplicantQueryServiceTest {

    @InjectMocks
    GetDirectedDonationApplicantQueryService getDirectedDonationApplicantQueryService;

    @Mock
    DonationHistoryRepository donationHistoryRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    BloodRequestRepository bloodRequestRepository;

    @Test
    @DisplayName("지정 헌혈 신청자 조회에 성공한다.")
    void getDirectedDonationApplicant_success(){
        //given
        List<DirectedDonationApplicantView> expect = new ArrayList<>();
        DirectedDonationApplicantView view = DirectedDonationApplicantView.builder()
                .cid("111")
                .name("고봉")
                .phone("010-1111-2222")
                .donationStatus(null)
                .build();
        expect.add(view);

        given(bloodRequestRepository.existsByCidAndRequestId(any(), any())).willReturn(true);
        given(donationHistoryRepository.findByRequestIdAndDonationType(any(), any())).willReturn(commonDonationHistoryList_Directed());
        given(userRepository.getUserInfo(any())).willReturn(commonUserInfoView());

        //when
        List<DirectedDonationApplicantView> result = getDirectedDonationApplicantQueryService.getDirectedDonationApplicant("111", 111L);

        // then
        assertThat(result).usingRecursiveComparison().isEqualTo(expect);
    }

    @Test
    @DisplayName("지정 헌혈 신청자 조회에 실패한다.")
    void getDirectedDonationApplicant_fail(){
        //given
        given(userRepository.getUserInfo(any())).willThrow();

        //when
        Throwable throwable = assertThrows(Exception.class, () -> getDirectedDonationApplicantQueryService.getDirectedDonationApplicant("111", 111L));

        // then
        assertEquals(throwable.getMessage(), FAIL_GET_DIRECTED_DONATION_APPLICANT.getMessage());
    }
}
