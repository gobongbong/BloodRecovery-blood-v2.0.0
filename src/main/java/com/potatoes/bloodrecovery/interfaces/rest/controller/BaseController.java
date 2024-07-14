package com.potatoes.bloodrecovery.interfaces.rest.controller;

import org.springframework.http.HttpHeaders;

import static com.potatoes.constants.ResponseCode.SUCCESS;
import static com.potatoes.constants.StaticValues.HTTP_STATUS;
import static com.potatoes.constants.StaticValues.RESULT_MESSAGE;


public class BaseController {

    protected HttpHeaders getSuccessHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(RESULT_MESSAGE, SUCCESS.getUrlEncodingMessage());
        headers.set(HTTP_STATUS, String.valueOf(SUCCESS.getHttpStatus().value()));
        return headers;
    }
}
