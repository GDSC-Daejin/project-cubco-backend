package org.cubco.coupon.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.cubco.auth.resolver.UserId;
import org.cubco.coupon.dto.response.CouponDetailRes;
import org.cubco.coupon.dto.request.CouponImageUpdateReq;
import org.cubco.coupon.dto.response.CouponRes;
import org.cubco.coupon.dto.request.CouponUseReq;
import org.cubco.response.CommonResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "쿠폰 관련 API", description = "쿠폰 관련 API 모음입니다.")
public interface CouponApiDocs {

    // 1. 사용자 보유 쿠폰 리스트 조회
    @Operation(summary = "사용자 보유 쿠폰 리스트", description = "사용자가 보유한 쿠폰 리스트들이 잘 조회되는지 확인합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "보유 쿠폰 목록 조회 성공",
                    content = @Content(schema = @Schema(implementation = CouponRes.class))
            ),
            @ApiResponse(responseCode = "401", description = "인증이 필요합니다"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류가 발생했습니다.")
    })
    CommonResponse<List<CouponRes>> getUserCoupons(@UserId Long userId);

    // 2. 쿠폰 상세 조회
    @Operation(summary = "쿠폰 상세 조회", description = "쿠폰 상세조회가 잘 되는지 확인합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "쿠폰 상세 조회 성공",
                    content = @Content(schema = @Schema(implementation = CouponDetailRes.class))
            ),
            @ApiResponse(responseCode = "401", description = "인증이 필요합니다"),
            @ApiResponse(responseCode = "404", description = "존재하는 쿠폰을 찾을 수 없습니다."),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류가 발생했습니다.")
    })
    CommonResponse<CouponDetailRes> getCouponDetail(
//            @UserId Long userId, // PR 후 주석해제
            Long userId,
            @PathVariable Long couponId
    );

    // 3. 쿠폰 이미지 변경
    @Operation(summary = "쿠폰 이미지 변경", description = "쿠폰 이미지 변경이 잘 되는지 확인합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "쿠폰 이미지가 변경되었습니다."),
            @ApiResponse(responseCode = "400", description = "요청데이터가 유효하지 않습니다."),
            @ApiResponse(responseCode = "401", description = "인증이 필요합니다."),
            @ApiResponse(responseCode = "403", description = "접근 권한이 없습니다."),
            @ApiResponse(responseCode = "404", description = "존재하는 쿠폰을 찾을 수 없습니다."),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류가 발생했습니다.")
    })
    CommonResponse<?> updateCouponImage(
//            @UserId Long userId, // PR 후 주석 해제
            Long userId,
            @PathVariable Long couponId,
            @RequestBody CouponImageUpdateReq request
    );

    // 4. 쿠폰 사용 (count 차감)
    @Operation(summary = "쿠폰 사용", description = "쿠폰 사용이 잘 되는지 확인합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "쿠폰이 사용되었습니다."),
            @ApiResponse(responseCode = "400", description = "입력값이 유효하지 않습니다."),
            @ApiResponse(responseCode = "401", description = "인증이 필요합니다."),
            @ApiResponse(responseCode = "403", description = "접근 권한이 없습니다."),
            @ApiResponse(responseCode = "404", description = "존재하는 쿠폰을 찾을 수 없습니다."),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류가 발생했습니다.")
    })
    CommonResponse<?> useCoupon(
//            @UserId Long userId, // PR 후 주석 해제
            Long userId,
            @PathVariable Long couponId,
            @RequestBody CouponUseReq request
    );
}


