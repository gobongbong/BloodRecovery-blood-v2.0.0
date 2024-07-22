package com.potatoes.bloodrecovery.infrastructure.jpa;

import com.potatoes.bloodrecovery.domain.model.aggregates.DonationHistory;
import com.potatoes.bloodrecovery.domain.repository.DonationHistoryRepository;
import com.potatoes.constants.DonationStatus;
import com.potatoes.constants.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonationHistoryJPARepository extends JpaRepository<DonationHistory, Long>, DonationHistoryRepository {
    List<DonationHistory> findByCid(String cid);
    List<DonationHistory> findByCidAndDonationTypeAndDonationStatus(String cid, String donationType, DonationStatus requestStatus);
    List<DonationHistory> findByRequestIdAndDonationType(Long requestId, String donationType);
    boolean existsByCidAndDonationTypeAndDonationStatus(String cid, String donationType, String requestStatus);
}
