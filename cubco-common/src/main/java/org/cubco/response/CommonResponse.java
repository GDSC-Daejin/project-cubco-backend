package org.cubco.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommonResponse<T> {

    private static final String SUCCESS_STATUS = "success";
    private static final String ERROR_STATUS = "error";

    private String status; // success, error
    private T data; // 성공 데이터 or 에러 상세정보
    private String message; // 사용자 메시지

    // 일반적인 성공 응답 생성 (데이터 포함)
    public static <T> CommonResponse<T> createSuccess(String message, T data) {
        return new CommonResponse<>(SUCCESS_STATUS, message, data);
    }

    // 응답 데이터 없이 성공 메시지만 반환
    public static CommonResponse<?> createSuccessWithNoContent(String message) {
        return new CommonResponse<>(SUCCESS_STATUS, message, null);
    }

    // 기본 에러 응답 생성 (에러 메시지만 포함, 코드 없음)
    public static CommonResponse<?> createError(String message) {
        return new CommonResponse<>(ERROR_STATUS, message, null);
    }

    // 에러 코드와 메시지를 포함한 에러 응답 생성
    public static CommonResponse<?> createError(String code, String message) {
        Map<String, String> errorData = new HashMap<>();
        errorData.put("code", code);
        errorData.put("message", message);
        return new CommonResponse<>(ERROR_STATUS, message, errorData);
    }

    // 유효성 검증 실패 응답 생성 (필드별 에러 메시지 포함)
    public static CommonResponse<?> createValidationError(BindingResult bindingResult) {
        Map<String, String> fieldErrors = new HashMap<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            fieldErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new CommonResponse<>(ERROR_STATUS, "요청 데이터가 유효하지 않습니다.", fieldErrors);
    }

    // 생성자 - 내부에서만 사용, 공통 응답 구조 초기화
    private CommonResponse(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
