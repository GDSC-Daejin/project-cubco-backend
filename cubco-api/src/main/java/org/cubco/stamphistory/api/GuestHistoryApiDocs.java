package org.cubco.stamphistory.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.cubco.response.CommonResponse;
import org.cubco.stamphistory.dto.request.user.GuestStampReq;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "쿠폰 적립 관련 API", description = "스탬프 적립 관련 API 모음입니다.")
public interface GuestHistoryApiDocs {

    @Operation(
            summary = "비회원 스탬프 적립 요청",
            description = """
                    ✅ **[전화번호 입력 후 스탬프 적립 요청 API]**
                    
                    - 유저가 QR 인증(유효성 검사) 후 → 전화번호 입력 후 "적립하기" 버튼 클릭 시 호출하는 API 입니다.
                    - QR 유효성 검사는 이전 단계에서 이미 완료된 상태입니다. (이 API 에서는 QR 관련 검사는 하지 않습니다.)
                    
                    **[프론트 처리 흐름 예시]**
                    1️⃣ 유저가 QR 찍고 → QR 유효성 검사 성공 → cafeId 확보
                    2️⃣ 전화번호 입력 → POST /stamp-histories/guest (이 API 호출)
                    3️⃣ 성공 시 "적립 성공 화면" 표시
                    
                    **Request Body 필드 설명:**  
                    - guestPhone: 유저가 입력한 전화번호 (숫자만, '-' 없이)  
                    - cafeId: QR 인증에서 받은 cafeId 값을 그대로 전달
                    """
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "비회원 적립 요청 성공",
                    content = @Content(schema = @Schema(implementation = CommonResponse.class))
            ),
            @ApiResponse(responseCode = "400", description = "유효하지 않은 요청입니다 (ex. 전화번호 누락 등)"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류가 발생했습니다.")
    })
    CommonResponse<?> guestCreate(@RequestBody GuestStampReq request);
}
