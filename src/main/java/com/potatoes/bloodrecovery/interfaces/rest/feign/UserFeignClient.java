package com.potatoes.bloodrecovery.interfaces.rest.feign;

import com.potatoes.bloodrecovery.domain.model.view.UserInfoView;
import com.potatoes.bloodrecovery.interfaces.rest.feign.config.CustomerFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import static com.potatoes.bloodrecovery.interfaces.rest.constants.apiurl.CustomerApiUrl.GET_USER_INFO;
import static com.potatoes.constants.StaticValues.HEADER_CID;

/**
 * 회원 Domain 호출
 */
@FeignClient(name="customer", url="http", configuration = CustomerFeignConfig.class)
public interface UserFeignClient {

    @GetMapping(GET_USER_INFO)
    ResponseEntity<UserInfoView> getUserInfo(@RequestHeader(value = HEADER_CID) String cid);
}
