package com.potatoes.bloodrecovery.interfaces.rest.feign;

import com.potatoes.bloodrecovery.domain.model.view.CustomerInfoDto;
import com.potatoes.bloodrecovery.interfaces.rest.feign.config.CustomerFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static com.potatoes.bloodrecovery.interfaces.rest.constants.CustomerApiUrl.GET_CUSTOMER_INFO;

/**
 * 회원 Domain 호출
 */
@FeignClient(name="customer", url="${feign.client.config.customer.endpoint}", configuration = CustomerFeignConfig.class)
public interface CustomerFeignClient { //todo user? customer?

    /**
     * 회원 정보 조회
     * @param customerId 고객번호
     * @return 회원 정보
     */
    @GetMapping(GET_CUSTOMER_INFO)
    ResponseEntity<CustomerInfoDto> findCustomerInfo(@PathVariable(name="customerId") String customerId);
}
