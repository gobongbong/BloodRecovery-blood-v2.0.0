package com.potatoes.bloodrecovery.application.commandservices;

import com.potatoes.bloodrecovery.domain.model.aggregates.BloodCard;
import com.potatoes.bloodrecovery.domain.model.aggregates.BloodRequest;
import com.potatoes.bloodrecovery.domain.model.commands.DonationBloodCardCommand;
import com.potatoes.bloodrecovery.domain.repository.BloodCardRepository;
import com.potatoes.bloodrecovery.domain.repository.BloodRequestRepository;
import com.potatoes.bloodrecovery.domain.repository.UserRepository;
import com.potatoes.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.potatoes.constants.ResponseCode.*;

@Service
@RequiredArgsConstructor
public class DonationBloodCardCommandService {

    private final UserRepository userRepository;
    private final BloodRequestRepository bloodRequestRepository;
    private final BloodCardRepository bloodCardRepository;

    @Transactional
    public void donationBloodCard(DonationBloodCardCommand donationBloodCardCommand) {
        //헌혈증 오래된것부터 차감되도록 해야함 -> 헌혈증 validation
        //헌혈증 기부하면 포인트 올라야함
        //헌혈 요청글 상태 변경되어야함
        //헌혈 요청글 기부받은 개수 변경 되어야함
        //헌혈증 소유자 변경되어야 함 -> 헌혈증 소유자 변경하고 히스토리 내역 쌓기!

        BloodRequest bloodRequest = bloodRequestRepository.findByRequestId(donationBloodCardCommand.getRequestId())
                .orElseThrow(() -> new ApiException(NO_BLOOD_REQUEST));

        List<BloodCard> validBloodCardList = getValidBloodCardList(donationBloodCardCommand);

        if (validateBloodCard(donationBloodCardCommand, validBloodCardList)){
            for (BloodCard bloodCard : validBloodCardList) {
                bloodCard.changeOwner(donationBloodCardCommand.getCid());
            }
        }else {
            throw new ApiException(NOT_VALID_CARD);
        }
    }

    public List<BloodCard> getValidBloodCardList(DonationBloodCardCommand donationBloodCardCommand){
        List<BloodCard> bloodCardList = bloodCardRepository.findByCid(donationBloodCardCommand.getCid());
        if (bloodCardList.isEmpty()){
            throw new ApiException(NO_BLOOD_CARD);
        }
        return bloodCardList.stream()
                .filter(BloodCard::isValidBloodCard)
                .sorted(Comparator.comparing(BloodCard::getDate).reversed())
                .limit(donationBloodCardCommand.getCardCnt())
                .collect(Collectors.toList());
    }

    //todo 메소드명 수정...
    public boolean validateBloodCard(DonationBloodCardCommand donationBloodCardCommand,List<BloodCard> validBloodCardList){
        return validBloodCardList.size() >= donationBloodCardCommand.getCardCnt();
    }
}
