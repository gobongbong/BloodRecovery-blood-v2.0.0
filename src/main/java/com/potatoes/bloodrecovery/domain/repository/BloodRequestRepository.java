package com.potatoes.bloodrecovery.domain.repository;

import com.potatoes.bloodrecovery.domain.model.aggregates.BloodRequest;
import com.potatoes.constants.RequestStatus;

public interface BloodRequestRepository {
    BloodRequest save(BloodRequest bloodRequest);
    BloodRequest findByRequestId(Long requestId);
    boolean existsByCidAndRequestStatus(String cid, RequestStatus requestStatus);
}
