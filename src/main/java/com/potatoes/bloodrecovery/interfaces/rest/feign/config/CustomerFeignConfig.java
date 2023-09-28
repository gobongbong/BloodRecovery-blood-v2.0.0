package com.potatoes.bloodrecovery.interfaces.rest.feign.config;

import com.potatoes.exception.ApiException;
import com.potatoes.utils.OpenFeignUtils;
import com.potatoes.utils.RequestScopeUtil;
import feign.FeignException;
import feign.Logger;
import feign.RequestInterceptor;
import feign.Retryer;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import static com.potatoes.utils.RequestScopeUtil.CUSTOMER_ID;

/**
 * Congiguration에서 설정해줘야 하는 bean (기본 제공되지 않는 bean)
 *  - Logger.Level
 *  - Retryer
 *  - ErrorDecoder
 *  - Request.Options
 *  - Collection<RequestInterceptor>
 *  - SetterFactory
 *
 * Configuration annotaion을 적용하면 전체 feign client에 적용됨.
 */
@Slf4j
@Configuration
public class CustomerFeignConfig {
    @Value("500")
    private int retryPeriod;
    @Value("1000")
    private int retryMaxPeriod;
    @Value("2")
    private int maxAttempts;

    /**
     * Debug log level
     */
    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }


    /**
     * Custom Header
     */
    @Bean
    public RequestInterceptor requestInterceptor(){
        return requestTemplate -> {
            requestTemplate.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            requestTemplate.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
            requestTemplate.header(CUSTOMER_ID, RequestScopeUtil.getCustomerId());
        };
    }

    /**
     * Error Decoder
     * feign RetryableException, IOException 등을 제외 하고 이곳에서 처리됨.
     */
    @Bean
    public ErrorDecoder decoder() {
        return (methodKey, response) -> {
            String responseCode = OpenFeignUtils.getHeaderResponseCode(response);
            String responseMessage = OpenFeignUtils.getHeaderResponseMessage(response);
            log.error("{} 요청이 성공하지 못했습니다. requestUrl: {}, requestBody: {}, responseBody: {}, responseCode: {}, responseMessage: {}");

            if(responseCode.equals(StringUtils.EMPTY) || responseMessage.equals(StringUtils.EMPTY))
                return FeignException.errorStatus(methodKey, response);

            return new ApiException(responseCode, URLDecoder.decode(responseMessage, StandardCharsets.UTF_8), HttpStatus.valueOf(response.status()));
        };
    }

    /**
     * retry 설정
     */
    @Bean
    public Retryer retryer(){
        return new Retryer.Default(retryPeriod, retryMaxPeriod, maxAttempts);
    }
}