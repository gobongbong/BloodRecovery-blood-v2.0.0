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
        //1. 헌혈증 요청글 조회
        //2. 유효한 헌혈증 목록 정렬해서 가져오기 (오래된 헌혈증부터 기불할 개수만큼)
        //3. 해당 헌혈증들 소유자 변경
        //4. 헌혈증 히스토리 등록 (소유자 변경 내역)
        //5. 기부 유저 포인트 증가
        //6. 헌혈증 요청글 상태, 기부받을 헌혈증 개수 변경

        BloodRequest bloodRequest = bloodRequestRepository.findByRequestId(donationBloodCardCommand.getRequestId())
                .orElseThrow(() -> new ApiException(NO_BLOOD_REQUEST));

        List<BloodCard> validBloodCardList = getValidBloodCardList(donationBloodCardCommand);
        if (validBloodCardList.isEmpty()){
            throw new ApiException(NOT_VALID_CARD);
        }

        for (BloodCard bloodCard : validBloodCardList) {
            bloodCard.changeOwner(donationBloodCardCommand.getCid());
            bloodCardRepository.save(bloodCard);
            BloodCardHistory bloodCardHistory = new BloodCardHistory(donationBloodCardCommand, bloodCard);
            bloodCardHistoryRepository.save(bloodCardHistory);
        }

        userRepository.requestPoint(donationBloodCardCommand.getCid(), POINT_PLUS, donationBloodCardCommand.getCardCnt());

        if (bloodRequest.getRequestStatus().equals(REGISTER)){
            bloodRequest.changeRequestStatus(ONGOING);
        }

        if (bloodRequest.getRequestStatus().equals(ONGOING)){
            //todo 요청글의 헌혈증 요청 개수를 다 채웠을때 완료로 상태 변경 필요 -> 완료처리하는 api 따로 존재하는데...
            if (bloodRequest.getBloodDonationCnt() + donationBloodCardCommand.getCardCnt() == bloodRequest.getBloodDonationCnt()){
                bloodRequest.changeRequestStatus(COMPLETE);
            }
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
}
