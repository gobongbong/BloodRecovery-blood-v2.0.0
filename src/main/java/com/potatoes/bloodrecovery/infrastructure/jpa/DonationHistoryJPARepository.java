package com.potatoes.bloodrecovery.infrastructure.jpa;

import com.potatoes.bloodrecovery.domain.model.aggregates.DonationHistory;
import com.potatoes.bloodrecovery.domain.repository.DonationHistoryRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationHistoryJPARepository extends JpaRepository<DonationHistory, Long>, DonationHistoryRepository {
}
