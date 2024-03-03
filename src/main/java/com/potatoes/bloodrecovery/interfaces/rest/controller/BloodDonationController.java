package com.potatoes.bloodrecovery.interfaces.rest.controller;

import com.potatoes.bloodrecovery.application.commandservices.DonationBloodCardCommandService;
import com.potatoes.bloodrecovery.application.queryservices.GetDonationHistoryQueryService;
import com.potatoes.bloodrecovery.domain.model.commands.DonationBloodCardCommand;
import com.potatoes.bloodrecovery.interfaces.rest.dto.DonationBloodCardReqDto;
import com.potatoes.bloodrecovery.interfaces.rest.dto.GetDonationHistoryRspDto;
import com.potatoes.bloodrecovery.interfaces.rest.mapper.DonationBloodMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.potatoes.bloodrecovery.interfaces.rest.constants.apiurl.BloodApiUrl.BLOOD_BASE_URL;
import static com.potatoes.bloodrecovery.interfaces.rest.constants.apiurl.BloodApiUrl.DONATION_BLOOD_CARD;
import static com.potatoes.constants.StaticValues.HEADER_CID;

@Slf4j
@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping(BLOOD_BASE_URL)
public class BloodDonationController extends BaseController{

    private final DonationBloodCardCommandService donationBloodCardCommandService;
    private final DonationBloodMapper donationBloodMapper;
    private final GetDonationHistoryQueryService getDonationHistoryQueryService;

    @PostMapping(DONATION_BLOOD_CARD)
    public ResponseEntity<Object> donationBloodCard(@RequestHeader(value = HEADER_CID) String cid, @RequestBody @Valid DonationBloodCardReqDto donationBloodCardReqDto) {
        DonationBloodCardCommand donationBloodCardCommand = donationBloodMapper.donationBloodCardReqtoCommand(cid, donationBloodCardReqDto);
        donationBloodCardCommandService.donationBloodCard(donationBloodCardCommand);
        return new ResponseEntity<>(getSuccessHeaders(), HttpStatus.OK);
    }

    @GetMapping(DONATION_BLOOD_CARD)
    public ResponseEntity<Object> getDonationHistory(@RequestHeader(value = HEADER_CID) String cid){
        GetDonationHistoryRspDto getDonationHistoryRspDto = GetDonationHistoryRspDto.builder()
                .donationHistory(getDonationHistoryQueryService.getDonationHistory(cid))
                .build();
        return new ResponseEntity<>(getDonationHistoryRspDto, getSuccessHeaders(), HttpStatus.OK);
    }
}
