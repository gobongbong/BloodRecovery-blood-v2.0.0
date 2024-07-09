package com.potatoes.bloodrecovery.interfaces.rest.constants.apiurl;

public class BloodApiUrl {

    public static final String BLOOD_BASE_URL = "/blood";

    /** 헌혈증 등록, 조회 */
    public static final String BLOOD_CARD = "/card";

    /** 헌혈증 삭제 */
    public static final String DELETE_BLOOD_CARD = "/card/{bloodCardId}";

    /** 헌혈증 개수 조회 */
    public static final String GET_BLOOD_CARD_COUNT = "/card/count";

    /** 헌혈 요청 글 등록, 목록 조회, 수정, 삭제, 상세 조회 */
    public static final String BLOOD_REQUEST = "/request";

    /** 헌혈증 기부 */
    public static final String DONATION_BLOOD_CARD = "/donation";

    /** 헌혈 요청 글 완료 */
    public static final String COMPLETE_BLOOD_REQUEST = "/request/{requestId}/complete";

    /** 지정 헌혈 완료 */
    public static final String COMPLETE_DIRECTED_DONATION = "/donation/direct/{requestId}/complete";

    /** 지정 헌혈 신청자 조회 */
    public static final String GET_DIRECTED_DONATION_APPLICANT = "/donation/direct/{requestId}";
}
