package org.cubco.qrcode.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.cubco.response.CommonResponse;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "비회원 QR 접근 관련 API", description = "유저가 QR 스캔 시 호출되는 API (QR 유효성 검사 및 cafeId 반환)")
public interface GuestQrAccessApiDocs {

    @Operation(
            summary = "Guest QR 접근 (유효성 검사)",
            description = """
                    ✅ **[유저가 QR 코드를 스캔했을 때 호출하는 API 입니다]**  
                    
                    - 유저가 QR 코드를 스캔하면 → QR URL 을 통해 이 API 가 호출됩니다.  
                    - 서버는 QRKey 유효성 검사 + 사용 여부 확인 후 → cafeId 를 반환합니다.  
                    - 이후 유저는 반환받은 cafeId 를 기반으로 전화번호 입력 화면으로 이동합니다.
                    
                    **주의사항:**  
                    - QR 유효성 검사 실패 / 이미 사용된 QR 은 오류 응답 반환합니다.  
                    - 적립 처리는 별도의 API (/stamp-histories/guest) 를 통해 진행됩니다.
                    """
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "QR 유효성 검사 성공 (cafeId 반환)",
                    content = @Content(schema = @Schema(implementation = Long.class))
            ),
            @ApiResponse(responseCode = "400", description = "유효하지 않은 요청입니다 (잘못된 QR 형식 등)"),
            @ApiResponse(responseCode = "404", description = "QR 코드가 유효하지 않습니다."),
            @ApiResponse(responseCode = "409", description = "이미 사용된 QR 코드입니다."),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류가 발생했습니다.")
    })
    CommonResponse<?> accessQR(@PathVariable String qrKey);
}
