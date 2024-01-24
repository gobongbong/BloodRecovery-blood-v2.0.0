package com.potatoes.bloodrecovery.interfaces.rest.constants.apiurl;

public class BloodApiUrl {

    public static final String BLOOD_BASE_URL = "/blood";

    /** 해당 회원의 헌혈 요청글 조회 */
    public static final String GET_CUSTOMER_REQUESTS = "/requests";

    /** 헌혈증 등록 */
    public static final String REGISTER_BLOOD_CARD = "/card";

    /** 헌혈증 OCR */
    public static final String BLOOD_CARD_OCR = "/card/ocr";

    /** 헌혈증 조회 */
    public static final String GET_BLOOD_CARDS = "/card";

    /** 헌혈증 삭제 */
    public static final String DELETE_BLOOD_CARD = "/card";

    /** 헌혈증 개수 조회 */
    public static final String GET_BLOOD_CARD_COUNT = "/card/count";

    /** 헌혈 요청 글 등록 */
    public static final String REGISTER_BLOOD_REQUEST = "/request";

    /** 헌혈 요청 글 수정 */
    public static final String MODIFY_BLOOD_REQUEST = "/request/{requestId}";

    /** 헌혈 요청 글 삭제 */
    public static final String DELETE_BLOOD_REQUEST = "/request/{requestId}";

    /** 헌혈 요청 글 상세 조회 */
    public static final String GET_BLOOD_REQUEST = "/request/{requestId}";

    /** 헌혈증 기부 */
    public static final String DONATION_BLOOD_CARD = "/donation";

    /** 헌혈 요청 글 완료 */
    public static final String COMPLETE_BLOOD_REQUEST = "/request/{requestId}/complete";
}
