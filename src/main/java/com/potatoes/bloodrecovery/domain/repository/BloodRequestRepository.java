package com.potatoes.bloodrecovery.domain.repository;

import com.potatoes.bloodrecovery.domain.model.aggregates.BloodRequest;
import com.potatoes.constants.RequestStatus;

import java.util.List;
import java.util.Optional;

public interface BloodRequestRepository {
    BloodRequest save(BloodRequest bloodRequest);
    Optional<BloodRequest> findByRequestId(Long requestId);
    Optional<BloodRequest> findByRequestIdAndCid(Long requestId, String cid);
    boolean existsByCidAndRequestStatusIn(String cid, List<RequestStatus> requestStatus);
}