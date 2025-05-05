package org.cubco.swagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.cubco.response.CommonResponse;
import org.springframework.http.ResponseEntity;

public interface TestApiDocs {

    @Operation(summary = "Response 테스트", description = "공통 응답 객체가 잘 동작하는지 확인합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "요청 성공")
    })
    CommonResponse<?> createUser();

    @Operation(summary = "예외 응답 테스트", description = "예외 발생 시 GlobalExceptionHandler가 작동하는지 확인합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    CommonResponse<Void> error();
}
