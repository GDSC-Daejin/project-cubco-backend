package org.cubco.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    // 400 Bad Request
    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "INVALID_REQUEST", "요청이 올바르지 않습니다."),
    //    DUPLICATED_EMAIL(HttpStatus.BAD_REQUEST, "DUPLICATED_EMAIL", "이미 등록된 이메일입니다."),
    MISSING_REQUIRED_FIELD(HttpStatus.BAD_REQUEST, "MISSING_REQUIRED_FIELD", "필수 입력값이 누락되었습니다."),
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "INVALID_INPUT_VALUE", "입력값이 유효하지 않습니다."),
    VALIDATION_FAILED(HttpStatus.BAD_REQUEST, "VALIDATION_FAILED", "요청 데이터가 유효하지 않습니다."),
    INVALID_FILE_EXTENSION(HttpStatus.BAD_REQUEST, "INVALID_FILE_EXTENSION", "지원하지 않는 확장자입니다."),
    INVALID_FILE_SIZE(HttpStatus.BAD_REQUEST, "INVALID_FILE_SIZE", "파일 크기가 너무 큽니다."),
    INVALID_FILE_EMPTY(HttpStatus.BAD_REQUEST, "INVALID_FILE_EMPTY", "파일이 비어 있습니다."),
    INVALID_IMAGE_URL(HttpStatus.BAD_REQUEST, "INVALID_IMAGE_URL", "잘못된 이미지 URL 입니다."),
    FAIL_DELETE_IMAGE(HttpStatus.BAD_REQUEST, "FAIL_DELETE_IMAGE", "파일 삭제에 실패하였습니다."),

    // 401 Unauthorized
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED", "인증이 필요합니다."),
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "TOKEN_EXPIRED", "토큰이 만료되었습니다."),
    TOKEN_INVALID(HttpStatus.UNAUTHORIZED, "TOKEN_INVALID", "유효하지 않은 토큰입니다."),

    // 403 Forbidden
    FORBIDDEN(HttpStatus.FORBIDDEN, "FORBIDDEN", "접근 권한이 없습니다."),
    ACCESS_DENIED(HttpStatus.FORBIDDEN, "ACCESS_DENIED", "이 기능에 접근할 수 없습니다."),
    CURATION_DELETE_ACCESS_DENIED(HttpStatus.FORBIDDEN, "CURATION_DELETE_ACCESS_DENIED", "해당 큐레이션을 삭제할 권한이 없습니다."),

    // 404 Not Found
    NOT_FOUND(HttpStatus.NOT_FOUND, "NOT_FOUND", "요청한 자원이 존재하지 않습니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER_NOT_FOUND", "해당 사용자를 찾을 수 없습니다."),
    COUPON_NOT_FOUND(HttpStatus.NOT_FOUND, "COUPON_NOT_FOUND", "존재하는 쿠폰을 찾을 수 없습니다."),
    ENTITY_NOT_FOUND(HttpStatus.NOT_FOUND, "ENTITY_NOT_FOUND", "대상을 찾을 수 없습니다."),
    IMAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "IMAGE_NOT_FOUND", "해당 이미지가 존재하지 않습니다"),
    CURATION_NOT_FOUND(HttpStatus.NOT_FOUND, "CURATION_NOT_FOUND", "큐레이션이 존재하지 않습니다"),
    TAG_NOT_FOUND(HttpStatus.NOT_FOUND, "TAG_NOT_FOUND", "태그가 존재하지 않습니다"),
    LIKE_NOT_FOUND(HttpStatus.NOT_FOUND, "LIKE_NOT_FOUND", "좋아요 내역이 존재하지 않습니다"),
//    MENU_NOT_FOUND(HttpStatus.NOT_FOUND, "MENU_NOT_FOUND", "해당 메뉴를 찾을 수 없습니다."),
//    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "POST_NOT_FOUND", "해당 게시글을 찾을 수 없습니다."),

    // 405 Method Not Allowed
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "METHOD_NOT_ALLOWED", "허용되지 않은 HTTP 메서드입니다."),

    // 500 Internal Server Error
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", "서버 내부 오류가 발생했습니다."),
    UNKNOWN_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "UNKNOWN_ERROR", "예기치 못한 오류가 발생했습니다."),
    FILE_UPLOAD_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "FILE_UPLOAD_FAIL", "파일 업로드에 실패했습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    ErrorCode(HttpStatus httpStatus, String code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}