package com.potatoes.bloodrecovery.infrastructure.jpa;

import com.potatoes.bloodrecovery.domain.model.aggregates.BloodCardHistory;
import com.potatoes.bloodrecovery.domain.repository.BloodCardHistoryRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BloodCardHistoryJPARepository extends JpaRepository<BloodCardHistory, String>, BloodCardHistoryRepository {
}
