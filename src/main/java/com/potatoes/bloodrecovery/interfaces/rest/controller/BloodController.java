package com.potatoes.bloodrecovery.interfaces.rest.controller;

import com.potatoes.bloodrecovery.application.queryservices.CustomerRequestsQueryService;
import com.potatoes.bloodrecovery.domain.model.queries.GetCustomerRequestsQuery;
import com.potatoes.bloodrecovery.interfaces.rest.dto.GetCustomerRequestsRspDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;

import static com.potatoes.bloodrecovery.interfaces.rest.constants.ValidationErrorMessage.EMPTY_HEADER_PARAMETER_CUSTOMER_ID;
import static com.potatoes.bloodrecovery.interfaces.rest.constants.apiurl.BloodApiUrl.BLOOD_BASE_URL;
import static com.potatoes.bloodrecovery.interfaces.rest.constants.apiurl.BloodApiUrl.GET_CUSTOMER_REQUESTS;
import static com.potatoes.constants.StaticValues.HEADER_CUSTOMER_ID;

@Slf4j
@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping(BLOOD_BASE_URL)
public class BloodController extends BaseController{

    private final CustomerRequestsQueryService customerRequestsQueryService;

    @GetMapping(GET_CUSTOMER_REQUESTS)
    public ResponseEntity<Object> getCustomerRequests(@RequestHeader(value = HEADER_CUSTOMER_ID) @NotEmpty(message = EMPTY_HEADER_PARAMETER_CUSTOMER_ID) String customerId, @PathVariable String userId) {
        GetCustomerRequestsRspDto getCustomerRequestsRspDto = GetCustomerRequestsRspDto.builder()
                .requests(customerRequestsQueryService.getCustomerRequests(GetCustomerRequestsQuery.builder()
                        .customerId(customerId)
                        .build()))
                .build();
        return new ResponseEntity<>(getCustomerRequestsRspDto, getSuccessHeaders(), HttpStatus.OK);
    }
}
