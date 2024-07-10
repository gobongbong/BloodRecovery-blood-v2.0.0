package com.potatoes.bloodrecovery.application.queryservices;

import com.potatoes.bloodrecovery.domain.model.aggregates.BloodRequest;
import com.potatoes.bloodrecovery.domain.model.view.BloodRequestView;
import com.potatoes.bloodrecovery.domain.repository.BloodRequestRepository;
import com.potatoes.bloodrecovery.domain.repository.UserRepository;
import com.potatoes.exception.ApiException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

import static com.potatoes.bloodrecovery.mock.MockDataUtil.commonBloodRequests;
import static com.potatoes.bloodrecovery.mock.MockDataUtil.commonUserInfoView;
import static com.potatoes.constants.ResponseCode.FAIL_GET_BLOOD_REQUEST_LIST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        Page<BloodRequest> page = new PageImpl<>(commonBloodRequests());

        given(bloodRequestRepository.findByRequestStatusIn(any(), any())).willReturn(page);
        given(userRepository.getUserInfo(any())).willReturn(commonUserInfoView());

        //when, then
        List<BloodRequestView> result = getBloodRequestsQueryService.getBloodRequests(1, 2);

        //then
        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("요청글 목록 조회에 실패한다.")
    void getBloodRequests_fail(){
        //given
        given(bloodRequestRepository.findByRequestStatusIn(any(), any())).willThrow();

        //when, then
        Throwable throwable = assertThrows(ApiException.class, () -> getBloodRequestsQueryService.getBloodRequests(1, 2));

        //then
        assertEquals(throwable.getMessage(), FAIL_GET_BLOOD_REQUEST_LIST.getMessage());
    }
}
