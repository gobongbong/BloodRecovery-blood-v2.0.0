package com.potatoes.bloodrecovery.application.queryservices;

import com.potatoes.bloodrecovery.domain.model.aggregates.Blood;
import com.potatoes.bloodrecovery.domain.model.queries.GetUserRequestsQuery;
import com.potatoes.bloodrecovery.domain.model.view.CustomerInfoView;
import com.potatoes.bloodrecovery.domain.model.view.UserRequestInfoView;
import com.potatoes.bloodrecovery.domain.repository.BloodRepository;
import com.potatoes.bloodrecovery.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserRequestsQueryService {

    private final BloodRepository bloodRepository;
    private final CustomerRepository customerRepository;

    public List<UserRequestInfoView> getUserRequest(GetUserRequestsQuery getUserRequestsQuery){
        List<Blood> bloodList = bloodRepository.findByCustomerId(getUserRequestsQuery.getCustomerId());

        CustomerInfoView customerInfoView = customerRepository.getCustomerInfo(getUserRequestsQuery.getCustomerId());

        List<UserRequestInfoView> userRequestInfoViewList = new ArrayList<>();

        bloodList.forEach(blood -> {
            UserRequestInfoView userRequestInfoView = UserRequestInfoView.builder()
                    .userNickname(customerInfoView.getUserNickname())
                    .gradeSn(customerInfoView.getGradeSn())
                    .bloodDonationCnt(blood.getBloodDonationCnt())
                    .bloodReqCnt(blood.getBloodReqCnt())
                    .bloodStatus(blood.getBloodStatus())
                    .post(blood.getPost())
                    .build();

            userRequestInfoViewList.add(userRequestInfoView);
        });

        //todo exeption 처리 추가

        return userRequestInfoViewList;
    }
}
