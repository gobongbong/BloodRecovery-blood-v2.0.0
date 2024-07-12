package com.potatoes.bloodrecovery.interfaces.rest.feign;

import com.potatoes.bloodrecovery.domain.model.view.UserInfoView;
import com.potatoes.bloodrecovery.interfaces.rest.dto.RequestPointReqDto;
import com.potatoes.bloodrecovery.interfaces.rest.feign.config.UserFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import static com.potatoes.bloodrecovery.interfaces.rest.constants.apiurl.CustomerApiUrl.GET_USER_INFO;
import static com.potatoes.bloodrecovery.interfaces.rest.constants.apiurl.CustomerApiUrl.POST_REQUEST_POINT;
import static com.potatoes.constants.StaticValues.HEADER_CID;

/**
 * 회원 Domain 호출
 */
@FeignClient(name="user", url="localhost:8081", configuration = UserFeignConfig.class)
public interface UserFeignClient {

    @GetMapping(GET_USER_INFO)
    ResponseEntity<UserInfoView> getUserInfo(@RequestHeader(value = HEADER_CID) String cid);
    @PostMapping (POST_REQUEST_POINT)
    void requestPoint(@RequestHeader(value = HEADER_CID) String cid, @RequestBody RequestPointReqDto requestPointReqDto);
}
