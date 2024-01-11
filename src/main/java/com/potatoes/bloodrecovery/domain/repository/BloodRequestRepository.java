package com.potatoes.bloodrecovery.domain.repository;

import com.potatoes.bloodrecovery.domain.model.aggregates.BloodRequest;

public interface BloodRequestRepository {
    BloodRequest save(BloodRequest bloodRequest);
    BloodRequest findByRequestId(Long requestId);
}
