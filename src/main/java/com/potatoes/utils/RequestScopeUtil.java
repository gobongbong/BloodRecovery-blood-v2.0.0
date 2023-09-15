package com.potatoes.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public class RequestScopeUtil {

    public static final String CUSTOMER_ID = "customerId";

    public static HttpServletRequest getHttpServletRequest() {
        RequestAttributes attr = RequestContextHolder.getRequestAttributes();
        if (attr == null) {
            //log.info("There is no HttpServletRequest object in RequestContextHolder");
            return null;
        }
        return ((ServletRequestAttributes) attr).getRequest();
    }


    public static String getCustomerId() {
        String customerId = "";
        HttpServletRequest request = getHttpServletRequest();
        if (request != null && StringUtils.isNotEmpty(request.getHeader(CUSTOMER_ID))) {
            customerId = request.getHeader(CUSTOMER_ID);
        }
        return customerId;
    }
}
