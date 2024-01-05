package com.potatoes.bloodrecovery.infrastructure.rest;

import com.potatoes.bloodrecovery.domain.model.view.UserInfoView;
import com.potatoes.bloodrecovery.domain.repository.UserRepository;
import com.potatoes.bloodrecovery.interfaces.rest.dto.RequestPointReqDto;
import com.potatoes.bloodrecovery.interfaces.rest.feign.UserFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserFeignClient userFeignClient;

    @Override
    public UserInfoView getUserInfo(String cid) {
        ResponseEntity<UserInfoView> responseEntity = userFeignClient.getUserInfo(cid);
        log.info("User에서 조회된 회원 정보: {}", responseEntity.getBody());
        return responseEntity.getBody();
    }

    @Override
    public void requestPoint(String cid, String sign, Integer point) {
        userFeignClient.requestPoint(cid,
                RequestPointReqDto.builder()
                        .point(point)
                        .sign(sign).build());
    }
}
