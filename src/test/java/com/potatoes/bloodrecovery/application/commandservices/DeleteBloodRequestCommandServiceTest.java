package com.potatoes.bloodrecovery.application.commandservices;

import com.potatoes.bloodrecovery.domain.repository.BloodRequestRepository;
import com.potatoes.bloodrecovery.domain.repository.UserRepository;
import com.potatoes.exception.ApiException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.potatoes.bloodrecovery.mock.MockDataUtil.commonBloodRequest;
import static com.potatoes.constants.RequestStatus.ONGOING;
import static com.potatoes.constants.RequestStatus.REGISTER;
import static com.potatoes.constants.ResponseCode.NOT_DELETE_BLOOD_REQUEST;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class DeleteBloodRequestCommandServiceTest {

    @InjectMocks
    DeleteBloodRequestCommandService deleteBloodRequestCommandService;

    @Mock
    BloodRequestRepository bloodRequestRepository;
    @Mock
    UserRepository userRepository;

    @Test
    @DisplayName("요청글 삭제에 성공한다.")
    void deleteBloodRequest_success(){
        //given
        given(bloodRequestRepository.findByRequestId(any())).willReturn(Optional.ofNullable(commonBloodRequest(REGISTER)));

        //when, then
        assertDoesNotThrow(() -> deleteBloodRequestCommandService.deleteBloodRequest("111", 111L));
    }

    @Test
    @DisplayName("요청글 삭제에 실패한다. (이미 기부가 진행 중인 요청 글)")
    void deleteBloodRequest_fail(){
        //given
        given(bloodRequestRepository.findByRequestId(any())).willReturn(Optional.ofNullable(commonBloodRequest(ONGOING)));

        //when
        Throwable throwable = assertThrows(ApiException.class, () -> deleteBloodRequestCommandService.deleteBloodRequest("111", 111l));

        //then
        assertEquals(throwable.getMessage(), NOT_DELETE_BLOOD_REQUEST.getMessage());
    }
}
