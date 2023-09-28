package com.potatoes.bloodrecovery.interfaces.rest.controller;

import com.potatoes.bloodrecovery.application.commandservices.BloodCardCommandService;
import com.potatoes.bloodrecovery.application.commandservices.BloodCardOcrCommandService;
import com.potatoes.bloodrecovery.application.queryservices.CustomerRequestsQueryService;
import com.potatoes.bloodrecovery.domain.model.commands.RegisterBloodCardCommand;
import com.potatoes.bloodrecovery.domain.model.queries.GetCustomerRequestsQuery;
import com.potatoes.bloodrecovery.domain.model.view.OcrView;
import com.potatoes.bloodrecovery.interfaces.rest.dto.BloodCardOcrRspDto;
import com.potatoes.bloodrecovery.interfaces.rest.dto.RegisterBloodCardReqDto;
import com.potatoes.bloodrecovery.interfaces.rest.dto.GetCustomerRequestsRspDto;
import com.potatoes.bloodrecovery.interfaces.rest.mapper.RegisterBloodCardMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import java.io.IOException;

import static com.potatoes.bloodrecovery.interfaces.rest.constants.ValidationErrorMessage.EMPTY_HEADER_PARAMETER_CUSTOMER_ID;
import static com.potatoes.bloodrecovery.interfaces.rest.constants.apiurl.BloodApiUrl.*;
import static com.potatoes.constants.StaticValues.HEADER_CUSTOMER_ID;

@Slf4j
@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping(BLOOD_BASE_URL)
public class BloodController extends BaseController{

    private final CustomerRequestsQueryService customerRequestsQueryService;
    private final BloodCardOcrCommandService bloodCardOcrCommandService;
    private final RegisterBloodCardMapper registerBloodCardMapper;
    private final BloodCardCommandService bloodCardCommandService;

    @GetMapping(GET_CUSTOMER_REQUESTS)
    public ResponseEntity<Object> getCustomerRequests(@RequestHeader(value = HEADER_CUSTOMER_ID) @NotEmpty(message = EMPTY_HEADER_PARAMETER_CUSTOMER_ID) String customerId) {
        GetCustomerRequestsRspDto getCustomerRequestsRspDto = GetCustomerRequestsRspDto.builder()
                .requests(customerRequestsQueryService.getCustomerRequests(GetCustomerRequestsQuery.builder()
                        .customerId(customerId)
                        .build()))
                .build();
        return new ResponseEntity<>(getCustomerRequestsRspDto, getSuccessHeaders(), HttpStatus.OK);
    }

    @PostMapping(POST_BLOOD_CARD_OCR)
    public ResponseEntity<Object> bloodCardOcr(@RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        OcrView ocrView = bloodCardOcrCommandService.bloodCardOcr(imageFile);
        BloodCardOcrRspDto bloodCardOcrRspDto = BloodCardOcrRspDto.builder()
                .ocrView(ocrView)
                .build();
        return new ResponseEntity<>(bloodCardOcrRspDto, getSuccessHeaders(), HttpStatus.OK);
    }

    @PostMapping(POST_REGISTER_BLOOD_CARD)
    public ResponseEntity<Object> registerBloodCard(@RequestHeader(value = HEADER_CUSTOMER_ID) String customerId, @RequestBody @Valid RegisterBloodCardReqDto registerBloodCardReqDto) {
        RegisterBloodCardCommand registerBloodCardCommand = registerBloodCardMapper.reqDtoToCommand(customerId, registerBloodCardReqDto);
        bloodCardCommandService.registerBloodCard(registerBloodCardCommand);
        return new ResponseEntity<>(getSuccessHeaders(), HttpStatus.OK);
    }
}
