package org.cubco.qrcode.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.cubco.qrcode.dto.CafeQrGenerateReq;
import org.cubco.qrcode.dto.GuestQrRes;
import org.cubco.response.CommonResponse;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "QR코드 관련 API", description = "관리자용 QR 생성 관련 API입니다.")
public interface QRApiDocs {

    @Operation(
            summary = "QR 코드 생성",
            description = """
                    ✅ **[사장님(MANAGER)용 QR 코드 생성 API]**  
                    
                    - **[MANAGER 권한 사용자만 호출 가능합니다]**
                    - 사장님(Manager)이 로그인 후 → QR 코드 발급 버튼 클릭 시 호출하는 API 입니다.  
                    - 해당 카페의 QR Key 를 발급하고, QR Code 이미지 및 QR URL 을 반환합니다.
                    
                    **[프론트 처리 흐름 예시]**  
                    1️⃣ 사장님 로그인 성공 후 → QR 발급 화면 이동  
                    2️⃣ "QR 코드 발급" 버튼 클릭 → POST /api/v1/qr/generate 호출 (카페 ID 포함)  
                    3️⃣ 응답으로 받은 QR Image 를 화면에 표시 (유저가 스캔 가능하게)
                    
                    **Request Body 필드 설명:**  
                    - cafeId: 사장님이 관리하는 카페 ID
                    
                    **응답:**  
                    - QR Image (base64)  
                    - QR Key (UUID)  
                    - QR URL (유저가 찍으면 접근하게 되는 URL)
                    """
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "QR 코드 생성 성공",
                    content = @Content(schema = @Schema(implementation = GuestQrRes.class))
            ),
            @ApiResponse(responseCode = "400", description = "유효하지 않은 요청입니다 (ex. 카페 ID 누락 등)"),
            @ApiResponse(responseCode = "401", description = "인증이 필요합니다."),
            @ApiResponse(responseCode = "403", description = "접근 권한이 없습니다 (MANAGER 권한 필요)."),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류가 발생했습니다.")
    })
    CommonResponse<?> generateGuestStampQR(@RequestBody CafeQrGenerateReq request);
}
