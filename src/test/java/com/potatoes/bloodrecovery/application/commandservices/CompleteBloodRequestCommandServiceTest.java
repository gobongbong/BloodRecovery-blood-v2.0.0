package com.potatoes.bloodrecovery.application.commandservices;

import com.potatoes.bloodrecovery.domain.repository.BloodRequestRepository;
import com.potatoes.bloodrecovery.domain.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.potatoes.bloodrecovery.mock.MockDataUtil.commonBloodRequest;
import static com.potatoes.bloodrecovery.mock.MockDataUtil.commonBloodRequest_Complete;
import static com.potatoes.constants.RequestStatus.REGISTER;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CompleteBloodRequestCommandServiceTest {

    @InjectMocks
    CompleteBloodRequestCommandService completeBloodRequestCommandService;

    @Mock
    BloodRequestRepository bloodRequestRepository;
    @Mock
    UserRepository userRepository;

    @Test
    @DisplayName("요청글 완료 처리에 성공한다.")
    void completeBloodRequest_success(){
        //given
        given(bloodRequestRepository.findByRequestId(any())).willReturn(Optional.ofNullable(commonBloodRequest(REGISTER)));

        //when, then
        assertDoesNotThrow(() -> completeBloodRequestCommandService.completeBloodRequest("111", 111L));
    }

    @Test
    @DisplayName("요청글 완료 처리에 성공한다. (요청한 기부가 모두 완료되어 포인트 페이백이 없는 경우)")
    void completeBloodRequest_success_complete(){
        //given
        given(bloodRequestRepository.findByRequestId(any())).willReturn(Optional.ofNullable(commonBloodRequest_Complete()));

        //when
        completeBloodRequestCommandService.completeBloodRequest("111", 111L);

        //then
        verify(userRepository, never()).requestPoint(any(), any(), any());
    }
}
