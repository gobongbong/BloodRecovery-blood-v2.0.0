package com.potatoes.bloodrecovery.application.queryservices;

import com.potatoes.bloodrecovery.domain.repository.BloodRequestRepository;
import com.potatoes.bloodrecovery.domain.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import static com.potatoes.bloodrecovery.mock.MockDataUtil.commonBloodRequests;
import static com.potatoes.bloodrecovery.mock.MockDataUtil.commonUserInfoView;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class GetBloodRequestsQueryServiceTest {

    @InjectMocks
    GetBloodRequestsQueryService getBloodRequestsQueryService;

    @Mock
    BloodRequestRepository bloodRequestRepository;
    @Mock
    UserRepository userRepository;

    @Test
    @DisplayName("요청글 목록 조회에 성공한다.")
    void getBloodRequests_success(){
        //given
//        given(bloodRequestRepository.findByRequestStatusIn(any(), any())).willReturn(commonBloodRequests());
//        given(userRepository.getUserInfo(any())).willReturn(commonUserInfoView());

        //when, then
//        Assertions.assertDoesNotThrow(() -> getBloodRequestsQueryService.getBloodRequests(1, 1));

        //then
    }

    @Test
    @DisplayName("요청글 목록 조회에 실패한다.")
    void getBloodRequests_fail(){
        //given

        //when

        //then
    }

}
