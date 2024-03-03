package com.potatoes.bloodrecovery.domain.repository;

import com.potatoes.bloodrecovery.domain.model.aggregates.DonationHistory;

import java.util.List;

public interface DonationHistoryRepository {
    DonationHistory save(DonationHistory donationHistory);
    List<DonationHistory> findByCid(String cid);
}
