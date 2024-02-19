package com.potatoes.bloodrecovery.domain.repository;

import com.potatoes.bloodrecovery.domain.model.aggregates.DonationHistory;

public interface DonationHistoryRepository {
    DonationHistory save(DonationHistory donationHistory);
}
