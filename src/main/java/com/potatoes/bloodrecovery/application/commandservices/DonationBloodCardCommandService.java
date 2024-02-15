package com.potatoes.bloodrecovery.application.commandservices;

import com.potatoes.bloodrecovery.domain.model.aggregates.BloodCard;
import com.potatoes.bloodrecovery.domain.model.aggregates.BloodCardHistory;
import com.potatoes.bloodrecovery.domain.model.aggregates.BloodRequest;
import com.potatoes.bloodrecovery.domain.model.commands.DonationBloodCardCommand;
import com.potatoes.bloodrecovery.domain.repository.BloodCardHistoryRepository;
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

import static com.potatoes.constants.RequestStatus.*;
import static com.potatoes.constants.ResponseCode.*;
import static com.potatoes.constants.StaticValues.POINT_PLUS;

@Service
@RequiredArgsConstructor
public class DonationBloodCardCommandService {

    private final UserRepository userRepository;
    private final BloodRequestRepository bloodRequestRepository;
    private final BloodCardRepository bloodCardRepository;
    private final BloodCardHistoryRepository bloodCardHistoryRepository;

    @Transactional
    public void donationBloodCard(DonationBloodCardCommand donationBloodCardCommand) {
        BloodRequest bloodRequest = bloodRequestRepository.findByRequestId(donationBloodCardCommand.getRequestId())
                .orElseThrow(() -> new ApiException(NO_BLOOD_REQUEST));

        List<BloodCard> validBloodCardList = getValidBloodCardList(donationBloodCardCommand);
        if (validBloodCardList.isEmpty()){
            throw new ApiException(NOT_VALID_CARD);
        }

        try {
            for (BloodCard bloodCard : validBloodCardList) {
                bloodCard.changeOwner(donationBloodCardCommand.getCid());
                bloodCardRepository.save(bloodCard);
                BloodCardHistory bloodCardHistory = new BloodCardHistory(donationBloodCardCommand, bloodCard);
                bloodCardHistoryRepository.save(bloodCardHistory);
            }

            userRepository.requestPoint(donationBloodCardCommand.getCid(), POINT_PLUS, 50 * donationBloodCardCommand.getCardCnt());
            changeRequestStatusAndDonationCnt(bloodRequest, donationBloodCardCommand);
            //todo 기부 이력 쌓기
        }catch (Exception e){
            throw new ApiException(FAIL_DONATE_BLOOD_CARD);
        }
    }

    private List<BloodCard> getValidBloodCardList(DonationBloodCardCommand donationBloodCardCommand){
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

    private void changeRequestStatusAndDonationCnt(BloodRequest bloodRequest, DonationBloodCardCommand donationBloodCardCommand){
        if (bloodRequest.getRequestStatus().equals(REGISTER)){
            bloodRequest.changeRequestStatus(ONGOING);
        }

        if (bloodRequest.getRequestStatus().equals(ONGOING)){
            if (bloodRequest.getBloodDonationCnt() + donationBloodCardCommand.getCardCnt() == bloodRequest.getBloodDonationCnt()){
                bloodRequest.changeRequestStatus(COMPLETE);
            }
        }
        bloodRequest.changeBloodDonationCnt(donationBloodCardCommand.getCardCnt());
    }
}
