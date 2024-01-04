package com.potatoes.bloodrecovery.application.queryservices;

import com.potatoes.bloodrecovery.domain.model.aggregates.Blood;
import com.potatoes.bloodrecovery.domain.model.queries.GetCustomerRequestsQuery;
import com.potatoes.bloodrecovery.domain.model.view.UserInfoView;
import com.potatoes.bloodrecovery.domain.model.view.CustomerRequestInfoView;
import com.potatoes.bloodrecovery.domain.repository.BloodRepository;
import com.potatoes.bloodrecovery.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerRequestsQueryService {

    private final BloodRepository bloodRepository;
    private final UserRepository userRepository;

    public List<CustomerRequestInfoView> getCustomerRequests(GetCustomerRequestsQuery getCustomerRequestsQuery){
        List<Blood> bloodList = new ArrayList<>();
        bloodList = bloodRepository.findByCid(getCustomerRequestsQuery.getCid())
                .orElse(bloodList);

        UserInfoView userInfoView = userRepository.getUserInfo(getCustomerRequestsQuery.getCid());

        List<CustomerRequestInfoView> customerRequestInfoViewList = new ArrayList<>();

        bloodList.forEach(blood -> {
            CustomerRequestInfoView customerRequestInfoView = CustomerRequestInfoView.builder()
                    .userNickname(userInfoView.getUserNickname())
                    .gradeSn(userInfoView.getGradeSn())
                    .bloodDonationCnt(blood.getBloodDonationCnt())
                    .bloodReqCnt(blood.getBloodReqCnt())
                    .bloodStatus(blood.getBloodStatus())
                    .post(blood.getPost())
                    .build();

            customerRequestInfoViewList.add(customerRequestInfoView);
        });

        return customerRequestInfoViewList;
    }
}
