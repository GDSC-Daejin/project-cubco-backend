package org.cubco.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.cubco.response.CommonResponse;
import org.cubco.exception.CustomException;
import org.cubco.exception.ErrorCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Operation(summary = "Response 테스트", description = "공통 응답 객체가 잘 동작하는지 확인합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "요청 성공")
    })
    @GetMapping("/success")
    public CommonResponse<?> createUser() {
        return CommonResponse.createSuccessWithNoContent("성공");
    }

    @Operation(summary = "예외 응답 테스트", description = "예외 발생 시 GlobalExceptionHandler가 작동하는지 확인합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @GetMapping("/error")
    public CommonResponse<Void> error() {
        throw new CustomException(ErrorCode.INVALID_REQUEST) {};
    }
}