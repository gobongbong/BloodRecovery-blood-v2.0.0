package com.potatoes.bloodrecovery.infrastructure.jpa;

import com.potatoes.bloodrecovery.domain.model.aggregates.DonationHistory;
import com.potatoes.bloodrecovery.domain.repository.DonationHistoryRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonationHistoryJPARepository extends JpaRepository<DonationHistory, Long>, DonationHistoryRepository {
    List<DonationHistory> findByCid(String cid);
}
