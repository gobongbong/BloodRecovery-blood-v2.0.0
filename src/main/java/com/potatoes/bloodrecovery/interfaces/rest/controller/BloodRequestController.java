package com.potatoes.bloodrecovery.interfaces.rest.controller;

import com.potatoes.bloodrecovery.application.commandservices.ModifyBloodRequestCommandService;
import com.potatoes.bloodrecovery.application.commandservices.RegisterBloodRequestCommandService;
import com.potatoes.bloodrecovery.application.queryservices.CustomerRequestsQueryService;
import com.potatoes.bloodrecovery.domain.model.commands.ModifyBloodRequestCommand;
import com.potatoes.bloodrecovery.domain.model.commands.RegisterBloodRequestCommand;
import com.potatoes.bloodrecovery.domain.model.queries.GetCustomerRequestsQuery;
import com.potatoes.bloodrecovery.interfaces.rest.dto.*;
import com.potatoes.bloodrecovery.interfaces.rest.mapper.BloodRequestMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import static com.potatoes.bloodrecovery.interfaces.rest.constants.ValidationErrorMessage.EMPTY_HEADER_PARAMETER_CID;
import static com.potatoes.bloodrecovery.interfaces.rest.constants.apiurl.BloodApiUrl.*;
import static com.potatoes.constants.StaticValues.HEADER_CID;

@Slf4j
@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping(BLOOD_BASE_URL)
public class BloodRequestController extends BaseController{

    private final CustomerRequestsQueryService customerRequestsQueryService;
    private final BloodRequestMapper bloodRequestMapper;
    private final RegisterBloodRequestCommandService registerBloodRequestCommandService;
    private final ModifyBloodRequestCommandService modifyBloodRequestCommandService;

    @GetMapping(GET_CUSTOMER_REQUESTS)
    //todo 추후 수정 필요
    public ResponseEntity<Object> getCustomerRequests(@RequestHeader(value = HEADER_CID) @NotEmpty(message = EMPTY_HEADER_PARAMETER_CID) String cid) {
        GetCustomerRequestsRspDto getCustomerRequestsRspDto = GetCustomerRequestsRspDto.builder()
                .requests(customerRequestsQueryService.getCustomerRequests(GetCustomerRequestsQuery.builder()
                        .build()))
                .build();
        return new ResponseEntity<>(getCustomerRequestsRspDto, getSuccessHeaders(), HttpStatus.OK);
    }

    @PostMapping(POST_REGISTER_BLOOD_REQUEST)
    public ResponseEntity<Object> registerBloodRequest(@RequestHeader(value = HEADER_CID) String cid, @RequestBody @Valid RegisterBloodRequestReqDto registerBloodRequestReqDto) {
        RegisterBloodRequestCommand registerBloodRequestCommand = bloodRequestMapper.registerReqtoCommand(cid, registerBloodRequestReqDto);
        registerBloodRequestCommandService.registerBloodRequest(registerBloodRequestCommand);
        return new ResponseEntity<>(getSuccessHeaders(), HttpStatus.OK);
    }

    @PostMapping(MODIFY_REGISTER_BLOOD_REQUEST)
    public ResponseEntity<Object> modifyBloodRequest(@RequestHeader(value = HEADER_CID) String cid,
                                                   @PathVariable Long requestId,
                                                   @RequestBody @Valid ModifyBloodRequestReqDto modifyBloodRequestReqDto) {
        ModifyBloodRequestCommand modifyBloodRequestCommand = bloodRequestMapper.modifyReqtoCommand(cid, requestId, modifyBloodRequestReqDto);
        modifyBloodRequestCommandService.modifyBloodRequest(modifyBloodRequestCommand);
        return new ResponseEntity<>(getSuccessHeaders(), HttpStatus.OK);
    }
}
