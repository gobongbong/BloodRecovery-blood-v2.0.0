package com.potatoes.bloodrecovery.interfaces.rest.constants.apiurl;

public class BloodApiUrl {

    public static final String BLOOD_BASE_URL = "/blood";

    /** 해당 회원의 헌혈 요청글 조회 */
    public static final String GET_USER_REQUESTS = BLOOD_BASE_URL + "/requests/{userId}";

}
