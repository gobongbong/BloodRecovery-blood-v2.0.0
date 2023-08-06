package com.potatoes.utils;

import com.amazonaws.util.CollectionUtils;
import com.potatoes.constants.Channel;
import com.potatoes.constants.ResponseCode;
import com.potatoes.exception.ApiException;
import feign.Response;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.potatoes.constants.ResponseCode.SERVER_ERROR;
import static com.potatoes.constants.ResponseCode.SUCCESS;
import static com.potatoes.constants.StaticValues.RESULT_CODE;
import static com.potatoes.constants.StaticValues.RESULT_MESSAGE;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OpenFeignUtils {
    public static String getRequestBody(Response response) {
        if (response.request().body() == null) {
            return "";
        }
        try {
            return new String(response.request().body(), StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            log.error("feign request body converting error - response: {}", response, e);
            return "";
        }
    }

    public static String getResponseBody(Response response) {
        if (response.body() == null) {
            return "";
        }
        try (InputStream responseBodyStream = response.body().asInputStream()) {
            return IOUtils.toString(responseBodyStream, StandardCharsets.UTF_8.name());
        } catch (IOException e) {
            log.error("feign response body converting error - response: {}", response, e);
            return "";
        }
    }

    public static String getHeaderResponseCode(Response response){
        return getResponseHeader(response, RESULT_CODE);
    }

    public static String getHeaderResponseMessage(Response response){
        return getResponseHeader(response, RESULT_MESSAGE);
    }

    private static String getResponseHeader(Response response, String headerName){
        if(response.headers() == null){
            return "";
        }
        Map<String, Collection<String>> headers = response.headers();
        Collection<String> headerValues = headers.get(headerName);
        String resultCode = "";
        if (!CollectionUtils.isNullOrEmpty(headerValues)) {
            String[] array = new String[headerValues.size()];
            headerValues.toArray(array);
            resultCode = array[0];
        }
        return resultCode;
    }

    public static String decodeUrlEncodingMessage(String message){
        return URLDecoder.decode(message, StandardCharsets.UTF_8);
    }

    public static String findHeaderValue(HttpHeaders headers, String headerName) {
        List<String> headerValue = headers.get(headerName);
        return Objects.nonNull(headerValue) && headerValue.size() > 0 ? headerValue.get(0) : null;
    }

    /**
     * HttpStatus 가 200일 경우 Header 의 ResponseCode 를 체크한다.
     * 성공 응답 값 체크는 default로 0000 으로 체크한다.
     */
    public static void checkHeaderResponseCode(ResponseEntity<?> response) {
        checkHeaderResponseCode(response, null);
    }

    /**
     * HttpStatus 가 200일 경우 Header 의 ResponseCode 를 체크한다.
     */
    public static void checkHeaderResponseCode(ResponseEntity<?> response, Channel channel) {
        String resultCode = findHeaderValue(response.getHeaders(), RESULT_CODE);
        String responseCodeByChannel = ResponseCode.getResponseCodeByChannel(channel, SUCCESS);
        if(!responseCodeByChannel.equals(resultCode)) {
            String resultMessage = StringUtils.defaultIfEmpty(OpenFeignUtils.findHeaderValue(response.getHeaders(), RESULT_MESSAGE), SERVER_ERROR.getMessage());
            throw new ApiException(resultCode, URLDecoder.decode(resultMessage, StandardCharsets.UTF_8));
        }
    }
}