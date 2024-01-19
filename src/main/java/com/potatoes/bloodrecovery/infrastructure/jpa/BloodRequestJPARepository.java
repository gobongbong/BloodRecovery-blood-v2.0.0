package com.potatoes.bloodrecovery.infrastructure.jpa;

import com.potatoes.bloodrecovery.domain.model.aggregates.BloodRequest;
import com.potatoes.bloodrecovery.domain.repository.BloodRequestRepository;
import com.potatoes.constants.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BloodRequestJPARepository extends JpaRepository<BloodRequest, String>, BloodRequestRepository {
    BloodRequest findByRequestId(Long requestId);
    boolean existsByCidAndRequestStatusIn(String cid, List<RequestStatus> requestStatus);
}
