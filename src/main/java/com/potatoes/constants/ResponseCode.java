package com.potatoes.constants;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Getter
public enum ResponseCode {

    SUCCESS( "정상 처리되었습니다.",HttpStatus.OK),
    SERVER_ERROR( "서비스 접속이 원활하지 않습니다. 잠시 후 다시 이용해 주세요.",HttpStatus.INTERNAL_SERVER_ERROR),
    NO_DATA("조회된 데이터가 없습니다.",HttpStatus.NOT_FOUND),
    NO_IMAGE("헌혈증 이미지 파일이 없습니다.",HttpStatus.NOT_FOUND),
    FAIL_OCR("헌혈증 OCR 인식에 실패했습니다.",HttpStatus.INTERNAL_SERVER_ERROR),
    NOT_VALID_CARD("유효하지 않은 헌혈증입니다.",HttpStatus.NO_CONTENT),
    FAIL_REGISTER_CARD("헌혈증 등록에 실패했습니다.",HttpStatus.INTERNAL_SERVER_ERROR),
    FAIL_DELETE_CARD("헌혈증 삭제에 실패했습니다.",HttpStatus.INTERNAL_SERVER_ERROR),
    NO_BLOOD_CARD("등록된 헌혈증이 존재하지 않습니다.",HttpStatus.NOT_FOUND),
    NO_VALID_BLOOD_CARD("유효한 헌혈증이 존재하지 않습니다.",HttpStatus.NOT_FOUND),
    FAIL_REGISTER_BLOOD_REQUEST("헌혈 요청 등록에 실패했습니다.",HttpStatus.INTERNAL_SERVER_ERROR),
    EXIST_BLOOD_REQUEST("이미 진행 중인 헌혈 요청이 존재합니다.",HttpStatus.BAD_REQUEST),
    NO_BLOOD_REQUEST("헌혈 요청 내역이 존재하지 않습니다.",HttpStatus.NOT_FOUND),
    FAIL_DELETE_BLOOD_REQUEST("헌혈 요청 삭제에 실패했습니다.",HttpStatus.INTERNAL_SERVER_ERROR),
    NOT_DELETE_BLOOD_REQUEST("이미 기부가 진행 중인 요청 글은 삭제가 불가능합니다.",HttpStatus.INTERNAL_SERVER_ERROR),
    FAIL_MODIFY_BLOOD_REQUEST("헌혈 요청 수정에 실패했습니다.",HttpStatus.INTERNAL_SERVER_ERROR),
    NOT_MODIFY_BLOOD_REQUEST("이미 기부가 진행 중인 요청글은 수정이 불가능합니다.",HttpStatus.INTERNAL_SERVER_ERROR),
    FAIL_GET_BLOOD_REQUEST_DETAIL("헌혈 요청글 상세 조회에 실패했습니다.",HttpStatus.NOT_FOUND),
    FAIL_GET_BLOOD_REQUEST_LIST("헌혈 요청글 목록 조회에 실패했습니다.",HttpStatus.NOT_FOUND),
    FAIL_COMPLETE_BLOOD_REQUEST("헌혈 요청 완료 처리에 실패했습니다.",HttpStatus.INTERNAL_SERVER_ERROR),
    FAIL_DONATE_BLOOD_CARD("헌혈증 기부에 실패했습니다.",HttpStatus.INTERNAL_SERVER_ERROR),
    FAIL_GET_DIRECTED_DONATION_APPLICANT("지정 헌혈 신청자 목록 조회에 실패했습니다.",HttpStatus.INTERNAL_SERVER_ERROR),
    FAIL_GET_DONATION_HISTORY("헌혈 기부 이력 조회에 실패했습니다.",HttpStatus.INTERNAL_SERVER_ERROR),
    NO_BLOOD_DONATION("헌혈 기부 이력이 존재하지 않습니다.",HttpStatus.NOT_FOUND);

    private final String message;
    private final HttpStatus httpStatus;

    ResponseCode(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getUrlEncodingMessage(){
        return URLEncoder.encode(this.message, StandardCharsets.UTF_8);
    }
}
