package com.potatoes.bloodrecovery.application.queryservices;

import com.potatoes.bloodrecovery.domain.model.aggregates.BloodRequest;
import com.potatoes.bloodrecovery.domain.model.view.BloodRequestView;
import com.potatoes.bloodrecovery.domain.model.view.UserInfoView;
import com.potatoes.bloodrecovery.domain.repository.BloodRequestRepository;
import com.potatoes.bloodrecovery.domain.repository.UserRepository;
import com.potatoes.constants.RequestStatus;
import com.potatoes.bloodrecovery.exception.ApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.potatoes.constants.ResponseCode.FAIL_GET_BLOOD_REQUEST_LIST;
import static com.potatoes.constants.StaticValues.BLOOD_CARD_DONATION;
import static com.potatoes.constants.StaticValues.DIRECTED_DONATION;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetBloodRequestsQueryService {

    private final BloodRequestRepository bloodRequestRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<BloodRequestView> getBloodRequests(int pageNumber, int pageSize, int type) {
        List<BloodRequestView> bloodRequestViews = new ArrayList<>();
        String requestType = type == 1 ? BLOOD_CARD_DONATION : DIRECTED_DONATION;

        try {
            PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by("post.regDate").descending());

            Page<BloodRequest> bloodRequests = bloodRequestRepository.findByRequestTypeAndRequestStatusIn(pageRequest, requestType, RequestStatus.getOngoing());
            if (!bloodRequests.getContent().isEmpty()) {
                for (BloodRequest bloodRequest: bloodRequests) {
                    UserInfoView userInfoView = userRepository.getUserInfo(bloodRequest.getCid());
                    BloodRequestView bloodRequestView = new BloodRequestView(bloodRequest, userInfoView);
                    bloodRequestViews.add(bloodRequestView);
                }
            }
        }catch (Exception e){
            throw new ApiException(FAIL_GET_BLOOD_REQUEST_LIST);
        }

        return bloodRequestViews;
    }
}
