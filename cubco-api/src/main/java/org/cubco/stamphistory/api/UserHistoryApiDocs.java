package org.cubco.stamphistory.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.cubco.response.CommonResponse;
import org.cubco.stamphistory.dto.request.user.MemberStampDetailReq;
import org.cubco.stamphistory.dto.response.MemberStampHistoryDetailRes;
import org.cubco.stamphistory.dto.response.MemberStampHistoryListRes;
import java.util.List;

@Tag(name = "회원 쿠폰 적립 관련 API", description = "일반 회원(Member)의 적립 요청 관련 API 모음입니다.")
public interface UserHistoryApiDocs {

    @Operation(
            summary = "적립 요청 리스트 조회",
            description = """
                    ✅ **[유저 적립 요청 리스트 조회 API]**
                    
                    - 로그인한 유저가 자신이 요청한 적립 내역 리스트를 최신순으로 조회합니다.
                    - JWT 토큰을 통해 유저 ID를 추출하여 사용합니다.
                    """
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "적립 요청 리스트 조회 성공",
                    content = @Content(schema = @Schema(implementation = MemberStampHistoryListRes.class))
            ),
            @ApiResponse(responseCode = "403", description = "권한 없음"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    CommonResponse<List<MemberStampHistoryListRes>> getMyList(Long userId);

    @Operation(
            summary = "적립 요청 상세 조회",
            description = """
                    ✅ **[유저 적립 요청 상세 조회 API]**
                    
                    - 로그인한 유저가 자신이 요청한 특정 적립 내역의 상세 정보를 조회합니다.
                    - JWT 토큰으로 추출한 userId와 요청 body에 stampHistoryId를 전달합니다.
                    
                    **Request Body 필드 설명:**
                    - stampHistoryId: 조회할 적립 요청 ID
                    """
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "적립 요청 상세 조회 성공",
                    content = @Content(schema = @Schema(implementation = MemberStampHistoryDetailRes.class))
            ),
            @ApiResponse(responseCode = "403", description = "권한 없음"),
            @ApiResponse(responseCode = "404", description = "적립 요청을 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    CommonResponse<MemberStampHistoryDetailRes> getDetail(Long userId, MemberStampDetailReq memberStampDetailReq);
}
