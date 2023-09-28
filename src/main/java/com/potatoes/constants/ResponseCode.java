package com.potatoes.constants;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum ResponseCode {

    SUCCESS("0000", "정상 처리되었습니다.",HttpStatus.OK),
    SERVER_ERROR("0099", "서비스 접속이 원활하지 않습니다. 잠시 후 다시 이용해주세요.",HttpStatus.INTERNAL_SERVER_ERROR),
    NO_DATA("0009", "조회된 데이터가 없습니다.",HttpStatus.NOT_FOUND),
    NO_IMAGE("0008", "헌혈증 이미지 파일이 없습니다.",HttpStatus.NOT_FOUND),
    FAIL_OCR("0010", "헌혈증 OCR 인식에 실패했습니다.",HttpStatus.INTERNAL_SERVER_ERROR);

    private final String responseCode;
    private final String message;
    private final HttpStatus httpStatus;



    ResponseCode(String responseCode, String message, HttpStatus httpStatus) {

        this.responseCode = responseCode;
        this.message = message;
        this.httpStatus = httpStatus;

//        this.codeGroup = codeGroup;
//        this.code = code;
//        this.webResponseCode = webResponseCode;
    }

    private static final Map<String, ResponseCode> codes = Map.copyOf(
            Stream.of(values()).collect(Collectors.toMap(ResponseCode::getResponseCode, Function.identity())));

    public static String getResponseCodeByChannel(Channel channel, ResponseCode responseCode) {
        return responseCode.getResponseCode();
    }

    public static HttpStatus getHttpStatusFromResponseCode(String responseCode){
        if(codes.get(responseCode)!=null)
            return codes.get(responseCode).getHttpStatus();
        else
            return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public String getUrlEncodingMessage(){
        return URLEncoder.encode(this.message, StandardCharsets.UTF_8);
    }
}
