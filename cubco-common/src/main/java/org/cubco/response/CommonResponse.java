package org.cubco.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommonResponse<T> {

    private int status;            // HTTP 상태 코드 (200, 400, 404 등)
    private String code;           // 비즈니스 에러 코드 (ex: "USER_NOT_FOUND")
    private String message;        // 사용자 또는 클라이언트에게 보여줄 메시지
    private T data;                // 실제 응답 데이터 (성공일 경우에만 포함)

    // 성공 응답 생성 (데이터와 메시지 포함)
    public static <T> CommonResponse<T> createSuccess(HttpStatus httpStatus, String message, T data) {
        return new CommonResponse<>(httpStatus.value(), message, data);
    }

    // 성공 응답 생성 (데이터 없음, 메시지만 있는 경우)
    public static CommonResponse<?> createSuccessWithNoContent(HttpStatus httpStatus, String message) {
        return new CommonResponse<>(httpStatus.value(), message);
    }

    // 에러 응답 생성 (message만 포함)
    public static CommonResponse<?> createError(String message) {
        return new CommonResponse<>(message);
    }

    // 에러 응답 생성 (status, code, message 포함)
    public static CommonResponse<?> createError(HttpStatus httpStatus, String code, String message) {
        return new CommonResponse<>(httpStatus.value(), code, message);
    }

    // 유효성 검사 실패 시 필드별 에러 메시지 포함한 응답 생성
    public static CommonResponse<?> createValidationError(BindingResult bindingResult) {
        Map<String, String> fieldErrors = new HashMap<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            fieldErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new CommonResponse<>(HttpStatus.BAD_REQUEST.value(), "VALIDATION_FAILED", "요청 데이터가 유효하지 않습니다.", fieldErrors);
    }

    // 생성자: 성공 응답용 (data 포함)
    private CommonResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    // 생성자: 성공 응답용
    private CommonResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    // 생성자: 에러 응답용 (code, message, data 포함)
    private CommonResponse(int status, String code, String message, T data) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // 생성자: 에러 응답용 (code, message만 포함)
    private CommonResponse(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    // 생성자: 에러 응답용 (message만 포함)
    private CommonResponse(String message) {
        this.message = message;
    }
}
