package com.potatoes.bloodrecovery.application.commandservices;

import com.potatoes.bloodrecovery.domain.model.commands.RegisterBloodCardCommand;
import com.potatoes.bloodrecovery.domain.repository.BloodCardHistoryRepository;
import com.potatoes.bloodrecovery.domain.repository.BloodCardRepository;
import com.potatoes.bloodrecovery.exception.ApiException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static com.potatoes.constants.ResponseCode.FAIL_REGISTER_CARD;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class BloodCardCommandServiceTest {

    @InjectMocks
    BloodCardCommandService bloodCardCommandService;

    @Mock
    BloodCardRepository bloodCardRepository;
    @Mock
    BloodCardHistoryRepository bloodCardHistoryRepository;

    @Test
    @DisplayName("헌혈증 등록에 성공한다.")
    void registerBloodCard_success(){
        //given
        RegisterBloodCardCommand registerBloodCardCommand = RegisterBloodCardCommand.builder()
                .cid("aaaa")
                .code("1111")
                .date(LocalDate.now())
                .donationType("전혈")
                .name("고봉")
                .build();

        //when, then
        assertDoesNotThrow(() -> {
            bloodCardCommandService.registerBloodCard(registerBloodCardCommand);
        });
    }

    @Test
    @DisplayName("헌혈증 등록에 실패한다.")
    void registerBloodCard_fail(){
        //given
        RegisterBloodCardCommand registerBloodCardCommand = RegisterBloodCardCommand.builder()
                .cid("aaaa")
                .code("1111")
                .date(LocalDate.now())
                .donationType("전혈")
                .name("고봉")
                .build();

        given(bloodCardRepository.save(any())).willThrow();

        //when
        Throwable throwable = assertThrows(ApiException.class, () -> {
            bloodCardCommandService.registerBloodCard(registerBloodCardCommand);
        });

        //then
        assertEquals(throwable.getMessage(), FAIL_REGISTER_CARD.getMessage());
    }
}