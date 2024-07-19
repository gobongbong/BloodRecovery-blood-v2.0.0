package com.potatoes.bloodrecovery.application.queryservices;

import com.potatoes.bloodrecovery.domain.model.valueobjects.DirectedDonation;
import com.potatoes.bloodrecovery.domain.model.valueobjects.Post;
import com.potatoes.bloodrecovery.domain.model.view.BloodRequestDetailView;
import com.potatoes.bloodrecovery.domain.repository.BloodRequestRepository;
import com.potatoes.bloodrecovery.domain.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.potatoes.bloodrecovery.mock.MockDataUtil.commonBloodRequest;
import static com.potatoes.bloodrecovery.mock.MockDataUtil.commonUserInfoView;
import static com.potatoes.constants.RequestStatus.REGISTER;
import static com.potatoes.constants.StaticValues.DIRECTED_DONATION;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class GetBloodRequestDetailQueryServiceTest {

    @InjectMocks
    GetBloodRequestDetailQueryService getBloodRequestDetailQueryService;

    @Mock
    BloodRequestRepository bloodRequestRepository;
    @Mock
    UserRepository userRepository;

    @Test
    @DisplayName("요청글 상세 조회에 성공한다.")
    @Disabled
    void getBloodRequestDetail_success(){
        //given
        BloodRequestDetailView expect = BloodRequestDetailView.builder()
                .userNickname("gobong")
                .profileImage("aaaaa")
                .requestType(DIRECTED_DONATION)
                .editable(true)
                .bloodReqCnt(3)
                .bloodDonationCnt(0)
                .postInfo(new Post())
                .directInfo(new DirectedDonation())
                .build();

        given(bloodRequestRepository.findByRequestId(any())).willReturn(Optional.ofNullable(commonBloodRequest(REGISTER)));
        given(userRepository.getUserInfo(any())).willReturn(commonUserInfoView());

        //when
        BloodRequestDetailView result = getBloodRequestDetailQueryService.getBloodRequestDetail("111", 111L);

        //then
        assertThat(result).usingRecursiveComparison().isEqualTo(expect);
    }

    @Test
    @DisplayName("요청글 상세 조회에 성공한다. (수정 불가능)")
    @Disabled
    void getBloodRequestDetail_success_not_modify(){
        //given
        BloodRequestDetailView expect = BloodRequestDetailView.builder()
                .userNickname("gobong")
                .profileImage("aaaaa")
                .requestType(DIRECTED_DONATION)
                .editable(false)
                .bloodReqCnt(3)
                .bloodDonationCnt(0)
                .postInfo(new Post())
                .directInfo(new DirectedDonation())
                .build();

        given(bloodRequestRepository.findByRequestId(any())).willReturn(Optional.ofNullable(commonBloodRequest(REGISTER)));
        given(userRepository.getUserInfo(any())).willReturn(commonUserInfoView());

        //when
        BloodRequestDetailView result = getBloodRequestDetailQueryService.getBloodRequestDetail("222", 111L);

        //then
        assertThat(result).usingRecursiveComparison().isEqualTo(expect);
    }
}
