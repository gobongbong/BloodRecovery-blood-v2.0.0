package com.potatoes.bloodrecovery.interfaces.rest.controller;

import com.potatoes.bloodrecovery.interfaces.rest.dto.DonationBloodCardReqDto;
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

//    private final BloodRequestMapper bloodRequestMapper;

    @PostMapping(DONATION_BLOOD_CARD)
    public ResponseEntity<Object> donationBloodCard(@RequestHeader(value = HEADER_CID) String cid, @RequestBody @Valid DonationBloodCardReqDto donationBloodCardReqDto) {
        return new ResponseEntity<>(getSuccessHeaders(), HttpStatus.OK);
    }
}
