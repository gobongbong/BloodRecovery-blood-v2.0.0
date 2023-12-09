package com.potatoes.bloodrecovery.domain.repository;

import com.potatoes.bloodrecovery.domain.model.aggregates.Blood;

import java.util.List;
import java.util.Optional;

public interface BloodRepository {
    Optional<List<Blood>> findByCid(String cid);
}
