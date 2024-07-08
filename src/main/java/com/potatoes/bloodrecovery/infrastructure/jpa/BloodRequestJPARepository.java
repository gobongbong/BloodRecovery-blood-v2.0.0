package com.potatoes.bloodrecovery.infrastructure.jpa;

import com.potatoes.bloodrecovery.domain.model.aggregates.BloodRequest;
import com.potatoes.bloodrecovery.domain.repository.BloodRequestRepository;
import com.potatoes.constants.RequestStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BloodRequestJPARepository extends JpaRepository<BloodRequest, Long>, BloodRequestRepository {
    Optional<BloodRequest> findByRequestId(Long requestId);
    Optional<BloodRequest> findByRequestIdAndRequestStatusIn(Long requestId, List<RequestStatus> requestStatus);
    boolean existsByCidAndRequestStatusIn(String cid, List<RequestStatus> requestStatus);
    boolean existsByCidAndRequestId(String cid, Long requestId);
    Page<BloodRequest> findByRequestStatusIn(Pageable pageable, List<RequestStatus> requestStatus);
}
