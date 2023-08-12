package com.potatoes.exception;

import com.potatoes.constants.ResponseCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiException extends RuntimeException {

    private final String resultCode;
    private final String resultMessage;
    private final HttpStatus httpStatus;

    public ApiException(String resultCode, String resultMessage, HttpStatus httpStatus) {
        super("[" + resultCode + "] " + resultMessage);
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.httpStatus = httpStatus;
    }

    public ApiException(String resultCode, String resultMessage) {
        super("[" + resultCode + "] " + resultMessage);
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.httpStatus = ResponseCode.getHttpStatusFromResponseCode(resultCode);
    }

    public ApiException(ResponseCode responseCode){
        super("["+responseCode.getHttpStatus()+"]"+"["+responseCode.getResponseCode()+"] "+responseCode.getMessage());
        this.resultCode = responseCode.getResponseCode();
        this.resultMessage = responseCode.getMessage();
        this.httpStatus = responseCode.getHttpStatus();
    }
}
