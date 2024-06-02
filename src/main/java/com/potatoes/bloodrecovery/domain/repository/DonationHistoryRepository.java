package com.potatoes.bloodrecovery.domain.repository;

import com.potatoes.bloodrecovery.domain.model.aggregates.DonationHistory;
import com.potatoes.constants.RequestStatus;

import java.util.List;

public interface DonationHistoryRepository {
    DonationHistory save(DonationHistory donationHistory);
    List<DonationHistory> findByCid(String cid);
    List<DonationHistory> findByCidAndDonationTypeAndDonationStatus(String cid, String donationType, RequestStatus requestStatus);
    List<DonationHistory> findByRequestIdAndDonationType(Long requestId, String donationType);

}
