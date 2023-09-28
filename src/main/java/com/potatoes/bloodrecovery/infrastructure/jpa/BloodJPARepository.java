package com.potatoes.bloodrecovery.infrastructure.jpa;

import com.potatoes.bloodrecovery.domain.model.aggregates.Blood;
import com.potatoes.bloodrecovery.domain.repository.BloodRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BloodJPARepository extends JpaRepository<Blood, String>, BloodRepository {
    Optional<List<Blood>> findByCustomerId(String customerId);
}
