package org.cubco.exception;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.cubco.response.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

@Slf4j
@Hidden
@RestControllerAdvice
public class GlobalExceptionHandler {

    // [1] 비즈니스 로직 관련 사용자 정의 예외 처리
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CommonResponse<?>> handleCustomException(CustomException ex) {
        log.error("비즈니스 예외 발생", ex);
        ErrorCode errorCode = ex.getErrorCode();
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(CommonResponse.createError(errorCode.getCode(), errorCode.getMessage()));
    }

    // [2] 예상치 못한 서버 내부 예외 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonResponse<?>> handleException(Exception ex) {
        log.error("서버 내부 예외 발생", ex);
        ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(CommonResponse.createError(errorCode.getCode(), errorCode.getMessage()));
    }

    // [3] DTO 유효성 검사 실패 (ex: @Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CommonResponse<?>> handleValidationException(MethodArgumentNotValidException ex) {
        log.error("DTO 유효성 검사 실패", ex);
        BindingResult bindingResult = ex.getBindingResult();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(CommonResponse.createValidationError(bindingResult));
    }

    // [4] 지원되지 않는 HTTP 메서드 (ex: GET 대신 POST 요청 등)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<CommonResponse<?>> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
        log.error("지원되지 않는 HTTP 메서드 요청", ex);
        ErrorCode errorCode = ErrorCode.METHOD_NOT_ALLOWED;
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(CommonResponse.createError(errorCode.getCode(), errorCode.getMessage()));
    }

    // [5] JSON 파싱 오류 (ex: 잘못된 형식의 JSON Body)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<CommonResponse<?>> handleMessageNotReadable(HttpMessageNotReadableException ex) {
        log.error("요청 본문 파싱 실패", ex);
        ErrorCode errorCode = ErrorCode.INVALID_REQUEST;
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(CommonResponse.createError(errorCode.getCode(), errorCode.getMessage()));
    }

    // [6] 필수 쿼리 파라미터 누락
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<CommonResponse<?>> handleMissingParameter(MissingServletRequestParameterException ex) {
        log.error("필수 요청 파라미터 누락", ex);
        ErrorCode errorCode = ErrorCode.MISSING_REQUIRED_FIELD;
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(CommonResponse.createError(errorCode.getCode(), errorCode.getMessage()));
    }

    // [7] 단일 필드 유효성 검증 실패 (ex: @RequestParam 검증)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<CommonResponse<?>> handleConstraintViolation(ConstraintViolationException ex) {
        log.error("필드 유효성 제약 위반", ex);
        ErrorCode errorCode = ErrorCode.INVALID_INPUT_VALUE;
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(CommonResponse.createError(errorCode.getCode(), errorCode.getMessage()));
    }

    // [8] 접근 권한 없음 (인가 실패)
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<CommonResponse<?>> handleAccessDenied(AccessDeniedException ex) {
        log.error("접근 권한 없음", ex);
        ErrorCode errorCode = ErrorCode.ACCESS_DENIED;
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(CommonResponse.createError(errorCode.getCode(), errorCode.getMessage()));
    }
}
