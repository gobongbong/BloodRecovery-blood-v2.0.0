package com.potatoes.bloodrecovery.application.queryservices;

import com.potatoes.bloodrecovery.domain.model.aggregates.DonationHistory;
import com.potatoes.bloodrecovery.domain.model.view.DonationHistoryView;
import com.potatoes.bloodrecovery.domain.repository.DonationHistoryRepository;
import com.potatoes.exception.ApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.potatoes.constants.ResponseCode.FAIL_GET_DONATION_HISTORY;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetDonationHistoryQueryService {

    private final DonationHistoryRepository donationHistoryRepository;

    @Transactional(readOnly = true)
    public  List<DonationHistoryView> getDonationHistory(String cid){
        List<DonationHistoryView> historyList = new ArrayList<>();
        try {
            List<DonationHistory> donationHistories = donationHistoryRepository.findByCid(cid);

            for (DonationHistory donationHistory : donationHistories) {
                DonationHistoryView donationHistoryView = DonationHistoryView.builder()
                        .donationCnt(donationHistory.getDonationCnt())
                        .donationType(donationHistory.getDonationType())
                        .donationDate(donationHistory.getDate())
                        .build();
                historyList.add(donationHistoryView);
            }
        }catch (Exception e){
            throw new ApiException(FAIL_GET_DONATION_HISTORY);
        }

        return historyList;
    }
}
