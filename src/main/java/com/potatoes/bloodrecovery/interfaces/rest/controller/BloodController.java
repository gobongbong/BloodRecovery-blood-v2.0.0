package com.potatoes.bloodrecovery.interfaces.rest.controller;

import com.potatoes.bloodrecovery.application.queryservices.UserRequestsQueryService;
import com.potatoes.bloodrecovery.domain.model.queries.GetUserRequestsQuery;
import com.potatoes.bloodrecovery.interfaces.rest.dto.GetUserRequestsRspDto;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import static com.potatoes.bloodrecovery.interfaces.rest.constants.ValidationErrorMessage.EMPTY_HEADER_PARAMETER_CUSTOMER_ID;
import static com.potatoes.bloodrecovery.interfaces.rest.constants.apiurl.BloodApiUrl.GET_USER_REQUESTS;
import static com.potatoes.constants.StaticValues.HEADER_CUSTOMER_ID;

@Slf4j
@RestController
@RequiredArgsConstructor
@Validated
//todo requestMapping
public class BloodController extends BaseController{

    private final UserRequestsQueryService userRequestsQueryService;

    @GetMapping(GET_USER_REQUESTS)
    //todo user? customer? -> customer!!!!
    //todo path 수정
    public ResponseEntity<Object> getUserRequests(@RequestHeader(value = HEADER_CUSTOMER_ID) @NotEmpty(message = EMPTY_HEADER_PARAMETER_CUSTOMER_ID) String customerId) {
        GetUserRequestsRspDto getUserRequestsRspDto = GetUserRequestsRspDto.builder()
                .requests(userRequestsQueryService.getUserRequest(GetUserRequestsQuery.builder()
                        .customerId(customerId)
                        .build()))
                .build();
        return new ResponseEntity<>(getUserRequestsRspDto, getSuccessHeaders(), HttpStatus.OK);
    }
}
