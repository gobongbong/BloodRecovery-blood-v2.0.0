package com.potatoes.bloodrecovery.application.queryservices;

import com.potatoes.bloodrecovery.domain.model.view.BloodRequestView;
import com.potatoes.bloodrecovery.domain.repository.BloodRequestRepository;
import com.potatoes.bloodrecovery.domain.repository.UserRepository;
import com.potatoes.bloodrecovery.exception.ApiException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static com.potatoes.bloodrecovery.mock.MockDataUtil.*;
import static com.potatoes.constants.ResponseCode.FAIL_GET_DIRECTED_BLOOD_REQUEST_LIST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class GetDirectedBloodRequestsQueryServiceTest {
    @InjectMocks
    GetDirectedBloodRequestsQueryService getDirectedBloodRequestsQueryService;

    @Mock
    BloodRequestRepository bloodRequestRepository;
    @Mock
    UserRepository userRepository;

    @Test
    @DisplayName("지정 헌혈 요청글 목록 조회에 성공한다.")
    void getDirectedBloodRequests_success(){
        //given
        List<BloodRequestView> list = new ArrayList<>();
        list.add(commonBloodRequestView());
        List<BloodRequestView> expect = list;

        given(bloodRequestRepository.findByCidAndRequestType(any(), any())).willReturn(commonDirectedBloodRequests());
        given(userRepository.getUserInfo(any())).willReturn(commonUserInfoView());

        //when, then
        List<BloodRequestView> result = getDirectedBloodRequestsQueryService.getDirectedBloodRequests("111");

        //then
        assertThat(expect).usingRecursiveComparison().isEqualTo(result);
    }

    @Test
    @DisplayName("지정 헌혈 요청글 목록 조회에 실패한다.")
    void getDirectedBloodRequests_fail(){
        //given
        given(bloodRequestRepository.findByCidAndRequestType(any(), any())).willThrow();

        //when, then
        Throwable throwable = assertThrows(ApiException.class, () -> getDirectedBloodRequestsQueryService.getDirectedBloodRequests("111"));

        //then
        assertEquals(throwable.getMessage(), FAIL_GET_DIRECTED_BLOOD_REQUEST_LIST.getMessage());
    }

}
