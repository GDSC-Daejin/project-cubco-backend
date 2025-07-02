package org.cubco.stamphistory.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.cubco.response.CommonResponse;
import org.cubco.stamphistory.dto.request.manager.*;
import org.cubco.stamphistory.dto.request.user.MemberStampReq;
import org.cubco.stamphistory.dto.response.ManagerStampHistoryDetailRes;
import org.cubco.stamphistory.dto.response.ManagerStampListRes;
import org.cubco.stamphistory.dto.response.MemberStampRes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "관리자 쿠폰 적립 관련 API", description = "카페 관리자(Manager)의 적립 요청 관련 API 모음입니다.")
public interface ManagerHistoryApiDocs {

    @Operation(
            summary = "회원 적립 요청 생성",
            description = """
                    ✅ **[전화번호로 회원 적립 요청 생성 API]**
                    
                    - 손님의 전화번호를 입력받아 적립 요청을 생성합니다.
                    - 일반 유저는 사용할 수 없으며 Manager 권한이 필요합니다.
                    
                    **Request Body 필드 설명:**
                    - phone: 적립할 회원 전화번호
                    - cafeId: 적립 요청할 카페 ID
                    """
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "회원 적립 요청 생성 성공",
                    content = @Content(schema = @Schema(implementation = MemberStampRes.class))
            ),
            @ApiResponse(responseCode = "400", description = "잘못된 입력값"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    CommonResponse<MemberStampRes> createMemberStamp(@RequestBody MemberStampReq request);

    @Operation(
            summary = "적립 요청 리스트 조회",
            description = """
                    ✅ **[카페별 적립 요청 리스트 조회 API]**
                    
                    - 사장님이 본인이 운영 중인 카페의 적립 요청 목록을 조회합니다.
                    
                    **Request Body 필드 설명:**
                    - cafeId: 조회할 카페 ID
                    
                    **Query Params 설명:**
                    - size/page/sort: Spring Pageable 파라미터로 페이징 처리
                    """
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "적립 요청 리스트 조회 성공",
                    content = @Content(schema = @Schema(implementation = Page.class)) // 실제는 Page<ManagerStampListRes> 형태
            ),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "403", description = "권한 없음"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    CommonResponse<Page<ManagerStampListRes>> getAllStampHistory(
            Long managerId, @RequestBody StampHistoryListReq stampListReq, Pageable pageable);

    @Operation(
            summary = "적립 요청 세부 조회",
            description = """
                    ✅ **[적립 요청 상세 정보 조회 API]**
                    
                    - 사장님이 본인이 운영하는 카페의 적립 요청 상세 정보를 조회합니다.
                    
                    **Request Body 필드 설명:**
                    - stampHistoryId: 상세 조회할 적립 요청 ID
                    """
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "적립 요청 상세 조회 성공",
                    content = @Content(schema = @Schema(implementation = ManagerStampHistoryDetailRes.class))
            ),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "403", description = "권한 없음"),
            @ApiResponse(responseCode = "404", description = "적립 요청을 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    CommonResponse<ManagerStampHistoryDetailRes> getDetailForManager(
            Long managerId, @RequestBody StampHistoryDetailReq stampHistoryDetailReq);

    @Operation(
            summary = "적립 요청 승인",
            description = """
                    ✅ **[적립 요청 승인 API]**
                    
                    - 사장님이 적립 요청을 승인합니다.
                    
                    **Request Body 필드 설명:**
                    - stampHistoryId: 승인할 적립 요청 ID
                    """
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "적립 요청 승인 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "403", description = "권한 없음"),
            @ApiResponse(responseCode = "404", description = "적립 요청을 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    CommonResponse<?> approveStamp(Long managerId, @RequestBody StampHistoryApproveReq stampHistoryApproveReq);

    @Operation(
            summary = "적립 요청 거절",
            description = """
                    ✅ **[적립 요청 거절 API]**
                    
                    - 사장님이 적립 요청을 거절합니다.
                    
                    **Request Body 필드 설명:**
                    - stampHistoryId: 거절할 적립 요청 ID
                    """
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "적립 요청 거절 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "403", description = "권한 없음"),
            @ApiResponse(responseCode = "404", description = "적립 요청을 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    CommonResponse<?> rejectStamp(Long managerId, @RequestBody StampHistoryRejectReq stampHistoryRejectReq);

}
