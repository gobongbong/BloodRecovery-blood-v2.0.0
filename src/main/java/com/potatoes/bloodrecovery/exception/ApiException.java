package com.potatoes.bloodrecovery.exception;

import com.potatoes.constants.ResponseCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiException extends RuntimeException {

    private final String resultMessage;
    private final HttpStatus httpStatus;

    public ApiException(String resultMessage, HttpStatus httpStatus) {
        super("["+httpStatus+"]" + resultMessage);

        this.resultMessage = resultMessage;
        this.httpStatus = httpStatus;
    }

    public ApiException(ResponseCode responseCode){
        super(responseCode.getMessage());
        this.resultMessage = responseCode.getMessage();
        this.httpStatus = responseCode.getHttpStatus();
    }
}
