package com.potatoes.bloodrecovery.interfaces.rest.controller;

import com.potatoes.bloodrecovery.application.commandservices.CompleteBloodRequestCommandService;
import com.potatoes.bloodrecovery.application.commandservices.DeleteBloodRequestCommandService;
import com.potatoes.bloodrecovery.application.commandservices.ModifyBloodRequestCommandService;
import com.potatoes.bloodrecovery.application.commandservices.RegisterBloodRequestCommandService;
import com.potatoes.bloodrecovery.application.queryservices.GetBloodRequestDetailQueryService;
import com.potatoes.bloodrecovery.application.queryservices.GetBloodRequestsQueryService;
import com.potatoes.bloodrecovery.application.queryservices.GetDirectedBloodRequestsQueryService;
import com.potatoes.bloodrecovery.domain.model.commands.ModifyBloodRequestCommand;
import com.potatoes.bloodrecovery.domain.model.commands.RegisterBloodRequestCommand;
import com.potatoes.bloodrecovery.domain.model.view.BloodRequestDetailView;
import com.potatoes.bloodrecovery.domain.model.view.BloodRequestView;
import com.potatoes.bloodrecovery.interfaces.rest.dto.GetBloodRequestDetailRspDto;
import com.potatoes.bloodrecovery.interfaces.rest.dto.GetBloodRequestsRspDto;
import com.potatoes.bloodrecovery.interfaces.rest.dto.ModifyBloodRequestReqDto;
import com.potatoes.bloodrecovery.interfaces.rest.dto.RegisterBloodRequestReqDto;
import com.potatoes.bloodrecovery.interfaces.rest.mapper.BloodRequestMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

import static com.potatoes.bloodrecovery.interfaces.rest.constants.apiurl.BloodApiUrl.*;
import static com.potatoes.constants.StaticValues.HEADER_CID;

@Slf4j
@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping(BLOOD_BASE_URL)
public class BloodRequestController extends BaseController {

    private final BloodRequestMapper bloodRequestMapper;
    private final RegisterBloodRequestCommandService registerBloodRequestCommandService;
    private final ModifyBloodRequestCommandService modifyBloodRequestCommandService;
    private final DeleteBloodRequestCommandService deleteBloodRequestCommandService;
    private final GetBloodRequestDetailQueryService getBloodRequestDetailQueryService;
    private final CompleteBloodRequestCommandService completeBloodRequestCommandService;
    private final GetBloodRequestsQueryService getBloodRequestsQueryService;
    private final GetDirectedBloodRequestsQueryService getDirectedBloodRequestsQueryService;

    @PostMapping(BLOOD_REQUEST)
    public ResponseEntity<Object> registerBloodRequest(@RequestHeader(value = HEADER_CID) @NotBlank String cid,
                                                       @RequestBody @Valid RegisterBloodRequestReqDto registerBloodRequestReqDto) {
        RegisterBloodRequestCommand registerBloodRequestCommand = bloodRequestMapper.registerReqToCommand(cid, registerBloodRequestReqDto);
        registerBloodRequestCommandService.registerBloodRequest(registerBloodRequestCommand);
        return new ResponseEntity<>(getSuccessHeaders(), HttpStatus.OK);
    }

    @PatchMapping(BLOOD_REQUEST + "/{requestId}")
    public ResponseEntity<Object> modifyBloodRequest(@RequestHeader(value = HEADER_CID) @NotBlank String cid,
                                                     @PathVariable Long requestId,
                                                     @RequestBody @Valid ModifyBloodRequestReqDto modifyBloodRequestReqDto) {
        ModifyBloodRequestCommand modifyBloodRequestCommand = bloodRequestMapper.modifyReqToCommand(cid, requestId, modifyBloodRequestReqDto);
        modifyBloodRequestCommandService.modifyBloodRequest(modifyBloodRequestCommand);
        return new ResponseEntity<>(getSuccessHeaders(), HttpStatus.OK);
    }

    @PostMapping(BLOOD_REQUEST + "/{requestId}")
    public ResponseEntity<Object> deleteBloodRequest(@RequestHeader(value = HEADER_CID) @NotBlank String cid,
                                                     @PathVariable Long requestId) {
        deleteBloodRequestCommandService.deleteBloodRequest(cid, requestId);
        return new ResponseEntity<>(getSuccessHeaders(), HttpStatus.OK);
    }

    @GetMapping(BLOOD_REQUEST + "/{requestId}")
    public ResponseEntity<Object> getBloodRequestDetail(@RequestHeader(value = HEADER_CID) @NotBlank String cid,
                                                        @PathVariable Long requestId) {
        BloodRequestDetailView bloodRequestDetailView = getBloodRequestDetailQueryService.getBloodRequestDetail(cid, requestId);
        GetBloodRequestDetailRspDto getBloodRequestDetailRspDto = bloodRequestMapper.bloodRequestDetailViewToDto(bloodRequestDetailView);
        return new ResponseEntity<>(getBloodRequestDetailRspDto, getSuccessHeaders(), HttpStatus.OK);
    }

    @PatchMapping(COMPLETE_BLOOD_REQUEST)
    public ResponseEntity<Object> completeBloodRequest(@RequestHeader(value = HEADER_CID) @NotBlank String cid,
                                                       @PathVariable Long requestId) {
        completeBloodRequestCommandService.completeBloodRequest(cid, requestId);
        return new ResponseEntity<>(getSuccessHeaders(), HttpStatus.OK);
    }

    @GetMapping(BLOOD_REQUESTS)
    public ResponseEntity<Object> getBloodRequests(@RequestParam int pageSize, @RequestParam int pageNumber,
                                                   @PathVariable String requestType) {
        List<BloodRequestView> list = getBloodRequestsQueryService.getBloodRequests(pageNumber, pageSize, requestType);
        GetBloodRequestsRspDto getBloodRequestsRspDto = BloodRequestMapper.bloodRequestViewToDto(list);
        return new ResponseEntity<>(getBloodRequestsRspDto, getSuccessHeaders(), HttpStatus.OK);
    }

    @GetMapping(DIRECTED_BLOOD_REQUESTS)
    public ResponseEntity<Object> getDirectedBloodRequests(@RequestHeader(value = HEADER_CID) @NotBlank String cid) {
        List<BloodRequestView> list = getDirectedBloodRequestsQueryService.getDirectedBloodRequests(cid);
        GetBloodRequestsRspDto getBloodRequestsRspDto = BloodRequestMapper.bloodRequestViewToDto(list);
        return new ResponseEntity<>(getBloodRequestsRspDto, getSuccessHeaders(), HttpStatus.OK);
    }
}
