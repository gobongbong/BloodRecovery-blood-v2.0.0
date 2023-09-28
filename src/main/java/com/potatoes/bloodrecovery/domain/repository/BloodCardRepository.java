package com.potatoes.bloodrecovery.domain.repository;

import com.potatoes.bloodrecovery.domain.model.aggregates.BloodCard;

import java.util.List;
import java.util.Optional;

public interface BloodCardRepository {
    Optional<List<BloodCard>> findByCustomerId(String customerId);
    BloodCard save(BloodCard bloodCard);
}
