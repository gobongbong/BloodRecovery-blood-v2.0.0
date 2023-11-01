package com.potatoes.bloodrecovery.application.commandservices;

import com.potatoes.bloodrecovery.domain.model.commands.RegisterBloodCardCommand;
import com.potatoes.bloodrecovery.domain.repository.BloodCardHistoryRepository;
import com.potatoes.bloodrecovery.domain.repository.BloodCardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

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
                .date("20230201")
                .donationType("전혈")
                .name("고봉")
                .build();

        //when, then
        assertDoesNotThrow(() -> {
            bloodCardCommandService.registerBloodCard(registerBloodCardCommand);
        });
    }
}