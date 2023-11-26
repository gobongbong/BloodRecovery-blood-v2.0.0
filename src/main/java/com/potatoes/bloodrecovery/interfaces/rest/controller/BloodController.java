package com.potatoes.bloodrecovery.interfaces.rest.controller;

import com.potatoes.bloodrecovery.application.commandservices.BloodCardCommandService;
import com.potatoes.bloodrecovery.application.commandservices.BloodCardOcrCommandService;
import com.potatoes.bloodrecovery.application.commandservices.DeleteBloodCardCommandService;
import com.potatoes.bloodrecovery.application.queryservices.CustomerRequestsQueryService;
import com.potatoes.bloodrecovery.application.queryservices.GetBloodCardsQueryService;
import com.potatoes.bloodrecovery.domain.model.commands.DeleteBloodCardCommand;
import com.potatoes.bloodrecovery.domain.model.commands.RegisterBloodCardCommand;
import com.potatoes.bloodrecovery.domain.model.queries.GetBloodCardsQuery;
import com.potatoes.bloodrecovery.domain.model.queries.GetCustomerRequestsQuery;
import com.potatoes.bloodrecovery.domain.model.queries.GetRequestsQuery;
import com.potatoes.bloodrecovery.domain.model.view.OcrView;
import com.potatoes.bloodrecovery.interfaces.rest.dto.*;
import com.potatoes.bloodrecovery.interfaces.rest.mapper.BloodCardMapper;
import com.potatoes.bloodrecovery.interfaces.rest.mapper.RequestsMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

import static com.potatoes.bloodrecovery.interfaces.rest.constants.apiurl.BloodApiUrl.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping(BLOOD_BASE_URL)
public class BloodController extends BaseController{

    private final CustomerRequestsQueryService customerRequestsQueryService;
    private final BloodCardOcrCommandService bloodCardOcrCommandService;
    private final BloodCardMapper bloodCardMapper;
    private final RequestsMapper requestsMapper;
    private final BloodCardCommandService bloodCardCommandService;
    private final GetBloodCardsQueryService getBloodCardsQueryService;
    private final DeleteBloodCardCommandService deleteBloodCardCommandService;

    @GetMapping(GET_CUSTOMER_REQUESTS)
    //todo 추후 수정 필요
    public ResponseEntity<Object> getCustomerRequests(@RequestBody @Valid GetRequestsReqDto getRequestsReqDto) {
        GetRequestsQuery getRequestsQuery = requestsMapper.getBloodCardsReqtoQuery(getRequestsReqDto);
        GetCustomerRequestsRspDto getCustomerRequestsRspDto = GetCustomerRequestsRspDto.builder()
                .requests(customerRequestsQueryService.getCustomerRequests(GetCustomerRequestsQuery.builder()
                        .cid(getRequestsQuery.getCid())
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
    public ResponseEntity<Object> registerBloodCard(@RequestBody @Valid RegisterBloodCardReqDto registerBloodCardReqDto) {
        RegisterBloodCardCommand registerBloodCardCommand = bloodCardMapper.registerReqtoCommand(registerBloodCardReqDto);
        bloodCardCommandService.registerBloodCard(registerBloodCardCommand);
        return new ResponseEntity<>(getSuccessHeaders(), HttpStatus.OK);
    }

    @GetMapping(GET_BLOOD_CARDS)
    public ResponseEntity<Object> getBloodCards(@RequestBody @Valid GetBloodCardsReqDto getBloodCardsReqDto) {
        GetBloodCardsQuery getBloodCardsQuery = bloodCardMapper.getBloodCardsReqtoQuery(getBloodCardsReqDto);
        GetBloodCardsRspDto getBloodCardsRspDto = getBloodCardsQueryService.getBloodCards(getBloodCardsQuery);
        return new ResponseEntity<>(getBloodCardsRspDto, getSuccessHeaders(), HttpStatus.OK);
    }

    @DeleteMapping(DELETE_BLOOD_CARD)
    public ResponseEntity<Object> deleteBloodCard(@RequestBody @Valid DeleteBloodCardReqDto deleteBloodCardReqDto) {
        DeleteBloodCardCommand deleteBloodCardCommand = bloodCardMapper.deleteReqtoCommand(deleteBloodCardReqDto);
        deleteBloodCardCommandService.deleteBloodCard(deleteBloodCardCommand);
        return new ResponseEntity<>(getSuccessHeaders(), HttpStatus.OK);
    }
}

