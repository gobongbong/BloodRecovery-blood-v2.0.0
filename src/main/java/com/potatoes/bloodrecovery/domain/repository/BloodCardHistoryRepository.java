package com.potatoes.bloodrecovery.domain.repository;

import com.potatoes.bloodrecovery.domain.model.aggregates.BloodCardHistory;

public interface BloodCardHistoryRepository {
    BloodCardHistory save(BloodCardHistory bloodCardHistory);
}
