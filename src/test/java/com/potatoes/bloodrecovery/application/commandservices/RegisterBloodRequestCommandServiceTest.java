package com.potatoes.bloodrecovery.application.commandservices;

import com.potatoes.bloodrecovery.domain.model.commands.RegisterBloodRequestCommand;
import com.potatoes.bloodrecovery.domain.repository.BloodRequestRepository;
import com.potatoes.bloodrecovery.domain.repository.UserRepository;
import com.potatoes.exception.ApiException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import static com.potatoes.constants.ResponseCode.EXIST_BLOOD_REQUEST;
import static com.potatoes.constants.ResponseCode.FAIL_REGISTER_BLOOD_REQUEST;
import static com.potatoes.constants.StaticValues.BLOOD_CARD_DONATION;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class RegisterBloodRequestCommandServiceTest {

    @InjectMocks
    RegisterBloodRequestCommandService registerBloodRequestCommandService;

    @Mock
    UserRepository userRepository;
    @Mock
    BloodRequestRepository bloodRequestRepository;

    @Test
    @DisplayName("헌혈 요청글 등록에 성공한다.")
    void registerBloodRequest_success(){
        //given
        RegisterBloodRequestCommand registerBloodRequestCommand = RegisterBloodRequestCommand.builder()
                .cid("111")
                .requestType(BLOOD_CARD_DONATION)
                .bloodReqCnt(3)
                .title("헌혈증 구해요")
                .contents("제발료")
                .build();

        given(bloodRequestRepository.existsByCidAndRequestStatusIn(any(), any())).willReturn(false);

        //when, then
        assertDoesNotThrow(() -> registerBloodRequestCommandService.registerBloodRequest(registerBloodRequestCommand));
    }

    @Test
    @DisplayName("헌혈 요청글 등록에 실패한다. (이미 진행중인 헌혈 요청글이 존재)")
    void registerBloodRequest_fail_exist(){
        //given
        RegisterBloodRequestCommand registerBloodRequestCommand = RegisterBloodRequestCommand.builder()
                .cid("111")
                .requestType(BLOOD_CARD_DONATION)
                .bloodReqCnt(3)
                .title("헌혈증 구해요")
                .contents("제발료")
                .build();

        given(bloodRequestRepository.existsByCidAndRequestStatusIn(any(), any())).willReturn(true);

        //when
        Throwable throwable = assertThrows(ApiException.class, () -> registerBloodRequestCommandService.registerBloodRequest(registerBloodRequestCommand));

        //then
        assertEquals(throwable.getMessage(), EXIST_BLOOD_REQUEST.getMessage());
    }

    @Test
    @DisplayName("헌혈 요청글 등록에 실패한다.")
    void registerBloodRequest_fail(){
        //given
        RegisterBloodRequestCommand registerBloodRequestCommand = RegisterBloodRequestCommand.builder()
                .cid("111")
                .requestType(BLOOD_CARD_DONATION)
                .bloodReqCnt(3)
                .title("헌혈증 구해요")
                .contents("제발료")
                .build();

        given(bloodRequestRepository.existsByCidAndRequestStatusIn(any(), any())).willReturn(false);
        given(bloodRequestRepository.save(any())).willThrow(DataIntegrityViolationException.class);

        //when
        Throwable throwable = assertThrows(ApiException.class, () -> registerBloodRequestCommandService.registerBloodRequest(registerBloodRequestCommand));

        //then
        assertEquals(throwable.getMessage(), FAIL_REGISTER_BLOOD_REQUEST.getMessage());
    }
}
