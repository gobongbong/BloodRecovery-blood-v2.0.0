package com.potatoes.bloodrecovery.application.queryservices;

import com.potatoes.bloodrecovery.domain.model.aggregates.BloodRequest;
import com.potatoes.bloodrecovery.domain.model.view.BloodRequestView;
import com.potatoes.bloodrecovery.domain.model.view.UserInfoView;
import com.potatoes.bloodrecovery.domain.repository.BloodRequestRepository;
import com.potatoes.bloodrecovery.domain.repository.UserRepository;
import com.potatoes.bloodrecovery.exception.ApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.potatoes.bloodrecovery.domain.model.view.UserInfoView.userInfoView;
import static com.potatoes.constants.ResponseCode.*;
import static com.potatoes.constants.StaticValues.DIRECTED_DONATION;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetDirectedBloodRequestsQueryService {

    private final BloodRequestRepository bloodRequestRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public  List<BloodRequestView> getDirectedBloodRequests(String cid){
        List<BloodRequestView> bloodRequestViews = new ArrayList<>();

        try {
            List<BloodRequest> bloodRequests = bloodRequestRepository.findByCidAndRequestType(cid, DIRECTED_DONATION);
            if (!bloodRequests.isEmpty()) {
                for (BloodRequest bloodRequest: bloodRequests) {
//                    UserInfoView userInfoView = userRepository.getUserInfo(bloodRequest.getCid());
                    UserInfoView userInfoView = userInfoView();
                    BloodRequestView bloodRequestView = new BloodRequestView(bloodRequest, userInfoView);
                    bloodRequestViews.add(bloodRequestView);
                }
            }
        }catch (Exception e){
            throw new ApiException(FAIL_GET_DIRECTED_BLOOD_REQUEST_LIST);
        }
        return bloodRequestViews;
    }
}
