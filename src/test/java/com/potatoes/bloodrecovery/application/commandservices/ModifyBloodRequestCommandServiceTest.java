package com.potatoes.bloodrecovery.application.commandservices;

import com.potatoes.bloodrecovery.domain.model.commands.ModifyBloodRequestCommand;
import com.potatoes.bloodrecovery.domain.model.valueobjects.DirectedDonation;
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
import static com.potatoes.constants.ResponseCode.NOT_MODIFY_BLOOD_REQUEST;
import static com.potatoes.constants.StaticValues.BLOOD_CARD_DONATION;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ModifyBloodRequestCommandServiceTest {

    @InjectMocks
    ModifyBloodRequestCommandService modifyBloodRequestCommandService;

    @Mock
    BloodRequestRepository bloodRequestRepository;
    @Mock
    UserRepository userRepository;

    @Test
    @DisplayName("요청글 수정에 성공한다.")
    void modifyBloodRequest_success(){
        //given
        ModifyBloodRequestCommand modifyBloodRequestCommand = ModifyBloodRequestCommand.builder()
                .cid("111")
                .requestType(BLOOD_CARD_DONATION)
                .bloodReqCnt(3)
                .title("헌혈증 구해요")
                .contents("제발료")
                .directInfo(new DirectedDonation())
                .requestId(111L)
                .build();

        given(bloodRequestRepository.findByRequestId(any())).willReturn(Optional.ofNullable(commonBloodRequest(REGISTER)));

        //when, then
        assertDoesNotThrow(() -> modifyBloodRequestCommandService.modifyBloodRequest(modifyBloodRequestCommand));
    }

    @Test
    @DisplayName("요청글 수정에 실패한다. (요청글 상태가 진행 중인 경우)")
    void modifyBloodRequest_fail(){
        //given
        ModifyBloodRequestCommand modifyBloodRequestCommand = ModifyBloodRequestCommand.builder()
                .cid("111")
                .requestType(BLOOD_CARD_DONATION)
                .bloodReqCnt(3)
                .title("헌혈증 구해요")
                .contents("제발료")
                .directInfo(new DirectedDonation())
                .requestId(111L)
                .build();

        given(bloodRequestRepository.findByRequestId(any())).willReturn(Optional.ofNullable(commonBloodRequest(ONGOING)));

        //then
        Throwable throwable = assertThrows(ApiException.class, () -> modifyBloodRequestCommandService.modifyBloodRequest(modifyBloodRequestCommand));

        //when
        assertEquals(throwable.getMessage(), NOT_MODIFY_BLOOD_REQUEST.getMessage());
    }
}
