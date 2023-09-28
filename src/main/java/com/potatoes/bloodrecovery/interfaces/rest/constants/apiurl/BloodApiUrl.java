package com.potatoes.bloodrecovery.interfaces.rest.constants.apiurl;

public class BloodApiUrl {

    public static final String BLOOD_BASE_URL = "/blood";

    /** 해당 회원의 헌혈 요청글 조회 */
    public static final String GET_CUSTOMER_REQUESTS = "/requests";

    /** 헌혈증 등록 */
    public static final String POST_REGISTER_BLOOD_CARD = "/card";

    /** 헌혈증 OCR */
    public static final String POST_BLOOD_CARD_OCR = "/card/ocr";

}
