package com.potatoes.bloodrecovery.domain.repository;

import com.potatoes.bloodrecovery.domain.model.aggregates.BloodRequest;
import com.potatoes.constants.RequestStatus;

import java.util.List;

public interface BloodRequestRepository {
    void save(BloodRequest bloodRequest);
    BloodRequest findByRequestId(Long requestId);
    boolean existsByCidAndRequestStatusIn(String cid, List<RequestStatus> requestStatus);
}
