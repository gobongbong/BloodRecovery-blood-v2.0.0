package com.potatoes.bloodrecovery.application.queryservices;

import com.potatoes.bloodrecovery.domain.model.aggregates.BloodRequest;
import com.potatoes.bloodrecovery.domain.model.view.BloodRequestDetailView;
import com.potatoes.bloodrecovery.domain.model.view.UserInfoView;
import com.potatoes.bloodrecovery.domain.repository.BloodRequestRepository;
import com.potatoes.bloodrecovery.domain.repository.UserRepository;
import com.potatoes.exception.ApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.potatoes.constants.ResponseCode.FAIL_GET_BLOOD_REQUEST_DETAIL;
import static com.potatoes.constants.ResponseCode.NO_BLOOD_REQUEST;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetBloodRequestDetailQueryService {

    private final BloodRequestRepository bloodRequestRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public BloodRequestDetailView getBloodRequestDetail(String cid, Long requestId) {
        boolean editable = false;
        BloodRequestDetailView bloodRequestDetailView;

        BloodRequest bloodRequest = bloodRequestRepository.findByRequestId(requestId)
                .orElseThrow(() -> new ApiException(NO_BLOOD_REQUEST));

        if (StringUtils.equals(bloodRequest.getCid(), cid))
            editable = true;

        try {
            UserInfoView userInfoView = userRepository.getUserInfo(bloodRequest.getCid());
            bloodRequestDetailView = new BloodRequestDetailView(bloodRequest, userInfoView, editable);
        } catch (Exception e) {
            throw new ApiException(FAIL_GET_BLOOD_REQUEST_DETAIL);
        }
        return bloodRequestDetailView;
    }
}
