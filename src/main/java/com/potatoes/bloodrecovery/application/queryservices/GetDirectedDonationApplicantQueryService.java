package com.potatoes.bloodrecovery.application.queryservices;

import com.potatoes.bloodrecovery.domain.model.aggregates.DonationHistory;
import com.potatoes.bloodrecovery.domain.model.view.DirectedDonationApplicantView;
import com.potatoes.bloodrecovery.domain.model.view.UserInfoView;
import com.potatoes.bloodrecovery.domain.repository.BloodRequestRepository;
import com.potatoes.bloodrecovery.domain.repository.DonationHistoryRepository;
import com.potatoes.bloodrecovery.domain.repository.UserRepository;
import com.potatoes.exception.ApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.potatoes.constants.ResponseCode.FAIL_GET_DIRECTED_DONATION_APPLICANT;
import static com.potatoes.constants.StaticValues.DIRECTED_DONATION;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetDirectedDonationApplicantQueryService {

    private final DonationHistoryRepository donationHistoryRepository;
    private final UserRepository userRepository;
    private final BloodRequestRepository bloodRequestRepository;

    @Transactional(readOnly = true)
    public  List<DirectedDonationApplicantView> getDirectedDonationApplicant(String cid, Long requestId){
        List<DirectedDonationApplicantView> applicants = new ArrayList<>();
        try {
            if (bloodRequestRepository.existsByCidAndRequestId(cid, requestId)){
                List<DonationHistory> histories = donationHistoryRepository.findByRequestIdAndDonationType(requestId, DIRECTED_DONATION);
                if (histories.isEmpty()){
                    return applicants;
                }

                for (DonationHistory history: histories) {
                    UserInfoView userInfoView = userRepository.getUserInfo(history.getCid());
                    DirectedDonationApplicantView directedDonationApplicantView = DirectedDonationApplicantView.builder()
                            .cid(history.getCid())
                            .name(userInfoView.getName())
                            .phone(userInfoView.getPhone())
                            .donationStatus(history.getDonationStatus().getValue())
                            .build();
                    applicants.add(directedDonationApplicantView);
                }
            }
        }catch (Exception e){
            throw new ApiException(FAIL_GET_DIRECTED_DONATION_APPLICANT);
        }
        return applicants;
    }
}
