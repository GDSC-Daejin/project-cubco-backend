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
    private String code;    // 에러 코드 (예: USER_NOT_FOUND, VALIDATION_FAILED)

    public static <T> CommonResponse<T> createSuccess(String message, T data) {
        return new CommonResponse<>(SUCCESS_STATUS, data, message, null);
    }

    public static CommonResponse<?> createSuccessWithNoContent(String message) {
        return new CommonResponse<>(SUCCESS_STATUS, null, message, null);
    }

    // 예외 발생으로 API 호출 실패시 반환
    public static CommonResponse<?> createError(String message, String code) {
        return new CommonResponse<>(ERROR_STATUS, null, message, code);
    }

    // 유효성 검증 예외
    public static CommonResponse<?> createValidationError(BindingResult bindingResult) {
        Map<String, String> fieldErrors = new HashMap<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            fieldErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new CommonResponse<>(ERROR_STATUS, fieldErrors, "요청 데이터가 유효하지 않습니다.", "VALIDATION_FAILED");
    }

    private CommonResponse(String status, T data, String message, String code) {
        this.status = status;
        this.data = data;
        this.message = message;
        this.code = code;
    }
}