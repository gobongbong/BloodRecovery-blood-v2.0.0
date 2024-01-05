package com.potatoes.bloodrecovery.infrastructure.jpa;

import com.potatoes.bloodrecovery.domain.model.aggregates.BloodRequest;
import com.potatoes.bloodrecovery.domain.repository.BloodRequestRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BloodRequestJPARepository extends JpaRepository<BloodRequest, String>, BloodRequestRepository {
}
