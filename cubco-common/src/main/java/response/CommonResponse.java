package org.cubco.common.response;

public class CommonResponse<T> {
    private boolean success;
    private String code;
    private String message;
    private T data;

    public CommonResponse() {}

    public CommonResponse(boolean success, String code, String message, T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // 성공 응답 생성
    public static <T> CommonResponse<T> success(T data) {
        return new CommonResponse<>(true, null, "요청에 성공하였습니다.", data);
    }

    public static <T> CommonResponse<T> success(String message, T data) {
        return new CommonResponse<>(true, null, message, data);
    }

    // 실패 응답 생성
    public static <T> CommonResponse<T> fail(String code, String message) {
        return new CommonResponse<>(false, code, message, null);
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }
}