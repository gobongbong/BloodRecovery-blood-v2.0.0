package com.potatoes.bloodrecovery.application.commandservices;

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

import static com.potatoes.bloodrecovery.mock.MockDataUtil.commonBloodCard;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

        given(bloodCardRepository.findBloodCardByCidAndBloodCardId(any(), any())).willReturn(Optional.of(commonBloodCard()));

        //when, then
        assertDoesNotThrow(() -> {
            deleteBloodCardCommandService.deleteBloodCard(deleteBloodCardCommand);
        });
    }

    @Test
    @DisplayName("헌혈증 삭제에 실패한다.")
    void deleteBloodCard_fail(){
        //given
        DeleteBloodCardCommand deleteBloodCardCommand = DeleteBloodCardCommand.builder()
                .cid("aaaa")
                .bloodCardId(1L)
                .build();

        given(bloodCardRepository.findBloodCardByCidAndBloodCardId(any(), any())).willThrow();

        //when, then
        assertThrows(Exception.class, () -> deleteBloodCardCommandService.deleteBloodCard(deleteBloodCardCommand));
    }
}
