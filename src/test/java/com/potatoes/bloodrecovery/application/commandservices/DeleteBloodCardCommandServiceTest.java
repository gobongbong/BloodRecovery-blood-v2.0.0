package com.potatoes.bloodrecovery.application.commandservices;

import com.potatoes.bloodrecovery.domain.model.aggregates.BloodCard;
import com.potatoes.bloodrecovery.domain.model.commands.DeleteBloodCardCommand;
import com.potatoes.bloodrecovery.domain.repository.BloodCardHistoryRepository;
import com.potatoes.bloodrecovery.domain.repository.BloodCardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class DeleteBloodCardCommandServiceTest {

    @InjectMocks
    DeleteBloodCardCommandService deleteBloodCardCommandService;

    @Mock
    BloodCardRepository bloodCardRepository;
    @Mock
    BloodCardHistoryRepository bloodCardHistoryRepository;

    @Test
    @DisplayName("헌혈증 삭제에 성공한다.")
    void deleteBloodCard_success(){
        //given
        DeleteBloodCardCommand deleteBloodCardCommand = DeleteBloodCardCommand.builder()
                .cid("aaaa")
                .bloodCardId(1L)
                .build();

        BloodCard bloodCard = BloodCard.builder()
                .cid("aaaa")
                .code("1111")
                .date("20230201")
                .donationType("전혈")
                .name("고봉")
                .bloodCardId(1L)
                .build();

        given(bloodCardRepository.findBloodCardByCidAndBloodCardId(any(), any())).willReturn(Optional.of(bloodCard));

        //when, then
        assertDoesNotThrow(() -> {
            deleteBloodCardCommandService.deleteBloodCard(deleteBloodCardCommand);
        });
    }
}