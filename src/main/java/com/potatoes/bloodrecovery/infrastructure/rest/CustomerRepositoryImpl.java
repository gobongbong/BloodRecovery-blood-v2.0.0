package com.potatoes.bloodrecovery.infrastructure.rest;

import com.potatoes.bloodrecovery.domain.model.view.CustomerInfoView;
import com.potatoes.bloodrecovery.domain.repository.CustomerRepository;
import com.potatoes.bloodrecovery.interfaces.rest.feign.CustomerFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
@RequiredArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {

    private final CustomerFeignClient customerFeignClient;

    @Override
    public CustomerInfoView getCustomerInfo(String customerId) {
        ResponseEntity<CustomerInfoView> responseEntity = customerFeignClient.findCustomerInfo(customerId);
        log.info("Customer에서 조회된 회원 정보: {}", responseEntity.getBody());
        return responseEntity.getBody();
    }
}
