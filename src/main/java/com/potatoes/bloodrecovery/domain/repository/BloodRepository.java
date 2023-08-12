package com.potatoes.bloodrecovery.domain.repository;

import com.potatoes.bloodrecovery.domain.model.aggregates.Blood;

import java.util.List;

public interface BloodRepository {
    List<Blood> findByCustomerId(String customerId);
}
