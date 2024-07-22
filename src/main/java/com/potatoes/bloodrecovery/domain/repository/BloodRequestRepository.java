package com.potatoes.bloodrecovery.domain.repository;

import com.potatoes.bloodrecovery.domain.model.aggregates.BloodRequest;
import com.potatoes.constants.RequestStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BloodRequestRepository {
    BloodRequest save(BloodRequest bloodRequest);
    Optional<BloodRequest> findByRequestId(Long requestId);
    Optional<BloodRequest> findByRequestIdAndRequestStatusIn(Long requestId, List<RequestStatus> requestStatus);
    List<BloodRequest> findByCidAndRequestType(String cid, String requestType);
    boolean existsByCidAndRequestStatusIn(String cid, List<RequestStatus> requestStatus);
    boolean existsByCidAndRequestId(String cid, Long requestId);
    Page<BloodRequest> findByRequestTypeAndRequestStatusIn(Pageable pageable, String requestType, List<RequestStatus> requestStatus);
}
