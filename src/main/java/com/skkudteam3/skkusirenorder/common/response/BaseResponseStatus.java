package com.skkudteam3.skkusirenorder.common.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 에러 코드 관리
 */
@Getter
public enum BaseResponseStatus {
    /**
     * 200 : 요청 성공
     */
    SUCCESS(true, HttpStatus.OK.value(), "요청에 성공하였습니다."),

    // Cafeteria 관련
    CAFETERIA_CREATE(true, HttpStatus.CREATED.value(), "가게 생성에 성공하였습니다." ),
    CAFETERIA_OPEN_SUCCESS(true, HttpStatus.ACCEPTED.value(), "가게가 열렸습니다."),
    CAFETERIA_CLOSE_SUCCESS(true, HttpStatus.ACCEPTED.value(), "가게가 닫혔습니다."),
    CAFETERIA_UPDATE_SUCCESS(true, HttpStatus.ACCEPTED.value(), "가게 정보가 수정됐습니다."),

    // Menu 관련
    MENU_REGISTER_SUCCESS(true, HttpStatus.CREATED.value(), "해당 메뉴가 등록됐습니다."),

    // Order 관련
    ORDER_POST_SUCCESS(true, HttpStatus.CREATED.value(), "해당 PRE_WAITING 상태인 주문이 등록됐습니다."),
    ORDER_PAY_SUCCESS(true, HttpStatus.OK.value(), "해당 주문이 WAITING 상태로 변경됐습니다."),
    ORDER_ACCEPT_SUCCESS(true, HttpStatus.ACCEPTED.value(), "해당 주문이 PROCEEDING 상태로 변경됐습니다."),
    ORDER_COMPLETE_SUCCESS(true, HttpStatus.ACCEPTED.value(), "해당 주문이 COMPLETE 상태로 변경됐습니다."),
    ORDER_DENY_SUCCESS(true, HttpStatus.ACCEPTED.value(), "해당 주문이 CANCEL 상태로 변경됐습니다."),

    /**
     * 400 : Request, Response 오류
     */

    // Order 관련
    ORDER_MENU_COUNT_LACK(false,HttpStatus.BAD_REQUEST.value(),"최소 1개 이상의 상품을 선택해야합니다."),

    // Cafeteria 관련
    CAFETERIA_NOT_FOUND(false,HttpStatus.NOT_FOUND.value(),"해당 cafeteriaId를 가진 식당이 존재하지 않습니다."),

    RESPONSE_ERROR(false, HttpStatus.NOT_FOUND.value(), "값을 불러오는데 실패하였습니다."),

    DUPLICATED_EMAIL(false, HttpStatus.BAD_REQUEST.value(), "중복된 이메일입니다."),
    INVALID_MEMO(false,HttpStatus.NOT_FOUND.value(), "존재하지 않는 메모입니다."),
    FAILED_TO_LOGIN(false,HttpStatus.NOT_FOUND.value(),"없는 아이디거나 비밀번호가 틀렸습니다."),
    EMPTY_JWT(false, HttpStatus.UNAUTHORIZED.value(), "JWT를 입력해주세요."),
    INVALID_JWT(false, HttpStatus.UNAUTHORIZED.value(), "유효하지 않은 JWT입니다."),
    INVALID_USER_JWT(false,HttpStatus.FORBIDDEN.value(),"권한이 없는 유저의 접근입니다."),
    NOT_FIND_USER(false,HttpStatus.NOT_FOUND.value(),"일치하는 유저가 없습니다."),
    INVALID_OAUTH_TYPE(false, HttpStatus.BAD_REQUEST.value(), "알 수 없는 소셜 로그인 형식입니다."),



    /**
     * 500 :  Database, Server 오류
     */
    DATABASE_ERROR(false, HttpStatus.INTERNAL_SERVER_ERROR.value(), "데이터베이스 연결에 실패하였습니다."),
    SERVER_ERROR(false, HttpStatus.INTERNAL_SERVER_ERROR.value(), "서버와의 연결에 실패하였습니다."),
    PASSWORD_ENCRYPTION_ERROR(false, HttpStatus.INTERNAL_SERVER_ERROR.value(), "비밀번호 암호화에 실패하였습니다."),
    PASSWORD_DECRYPTION_ERROR(false, HttpStatus.INTERNAL_SERVER_ERROR.value(), "비밀번호 복호화에 실패하였습니다."),


    MODIFY_FAIL_USERNAME(false,HttpStatus.INTERNAL_SERVER_ERROR.value(),"유저네임 수정 실패"),
    DELETE_FAIL_USERNAME(false,HttpStatus.INTERNAL_SERVER_ERROR.value(),"유저 삭제 실패"),
    MODIFY_FAIL_MEMO(false,HttpStatus.INTERNAL_SERVER_ERROR.value(),"메모 수정 실패"),

    UNEXPECTED_ERROR(false, HttpStatus.INTERNAL_SERVER_ERROR.value(), "예상치 못한 에러가 발생했습니다.");


    private final boolean isSuccess;
    private final int code;
    private final String message;

    private BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
