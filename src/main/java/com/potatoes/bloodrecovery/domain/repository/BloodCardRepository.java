package com.potatoes.bloodrecovery.domain.repository;

import com.potatoes.bloodrecovery.domain.model.aggregates.BloodCard;

import java.util.List;
import java.util.Optional;

public interface BloodCardRepository {
    List<BloodCard> findByCustomerId(String customerId);
    Optional<BloodCard> findBloodCardByCustomerIdAndBloodCardId(String customerId, Long bloodCardId);
    BloodCard save(BloodCard bloodCard);
    BloodCard delete(Long bloodCardId);
}
