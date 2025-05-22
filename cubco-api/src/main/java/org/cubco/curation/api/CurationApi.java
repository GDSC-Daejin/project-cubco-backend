package org.cubco.curation.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.cubco.auth.resolver.UserId;
import org.cubco.curation.dto.request.CurationCreateReq;
import org.cubco.curation.dto.response.CurationCreateRes;
import org.cubco.curation.dto.response.CurationGetAllRes;
import org.cubco.curation.dto.response.CurationGetDetailRes;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "큐레이션 관련 API")
@SecurityRequirement(name = "Authorization")
public interface CurationApi {
    @Operation(
            summary = "큐레이션 전체조회 API",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "큐레이션 전체 조회에 성공했습니다.",
                            content = @Content(
                                    schema = @Schema(implementation = CurationGetAllRes.class),
                                    examples = @ExampleObject(value = """
                                            {
                                              "curations": [
                                                {
                                                  "curationId": 1,
                                                  "thumbnail": "https://example.com/curation/image1.jpg",
                                                  "title": "ddfdfdfdff",
                                                  "content": "dfdfdfdfdfdfdfdf",
                                                  "like": 0
                                                },
                                                {
                                                  "curationId": 2,
                                                  "thumbnail": "https://example.com/curation/image2.jpg",
                                                  "title": "ddfdfdfdff",
                                                  "content": "dfdfdfdfdfdfdfdf",
                                                  "like": 0
                                                },
                                                {
                                                  "curationId": 3,
                                                  "thumbnail": "https://example.com/curation/image3.jpg",
                                                  "title": "ddfdfdfdff",
                                                  "content": "dfdfdfdfdfdfdfdf",
                                                  "like": 0
                                                }
                                              ],
                                              "totalPages": 1,
                                              "totalElements": 3
                                            }
                                            """)
                            )
                    ),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청입니다.", content = @Content),
                    @ApiResponse(responseCode = "401", description = "액세스 토큰의 형식이 올바르지 않습니다. Bearer 타입을 확인해 주세요.", content = @Content),
                    @ApiResponse(responseCode = "401", description = "액세스 토큰의 값이 올바르지 않습니다.", content = @Content),
                    @ApiResponse(responseCode = "401", description = "액세스 토큰이 만료되었습니다. 재발급 받아주세요.", content = @Content),
                    @ApiResponse(responseCode = "405", description = "잘못된 HTTP method 요청입니다.", content = @Content),
                    @ApiResponse(responseCode = "500", description = "서버 내부 오류입니다.", content = @Content)})
    ResponseEntity<CurationGetAllRes> getAllCuration(Pageable pageable);

    @Operation(
            summary = "큐레이션 생성 API",
            responses = {
                    @ApiResponse(responseCode = "201",
                            content = @Content(
                                    schema = @Schema(implementation = CurationCreateRes.class),
                                    examples = @ExampleObject(value = """
                                            {
                                                "curationId": 1
                                            }
                                            """)
                            ), description = "큐레이션 등록에 성공하였습니다."),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청입니다.", content = @Content),
                    @ApiResponse(responseCode = "401", description = "액세스 토큰의 형식이 올바르지 않습니다. Bearer 타입을 확인해 주세요.", content = @Content),
                    @ApiResponse(responseCode = "401", description = "액세스 토큰의 값이 올바르지 않습니다.", content = @Content),
                    @ApiResponse(responseCode = "401", description = "액세스 토큰이 만료되었습니다. 재발급 받아주세요.", content = @Content),
                    @ApiResponse(responseCode = "405", description = "잘못된 HTTP method 요청입니다.", content = @Content),
                    @ApiResponse(responseCode = "500", description = "서버 내부 오류입니다.", content = @Content)
            }
    )
    ResponseEntity<CurationCreateRes> createCuration(@Parameter(hidden = true) @UserId Long userId,
                                                     @Parameter(description = "큐레이션 제목 및 내용") @RequestPart("curation") CurationCreateReq curation,
                                                     @Parameter(description = "큐레이션 이미지") @RequestPart("images") List<MultipartFile> images);

    @Operation(
            summary = "큐레이션 정보 조회 API",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "큐레이션 상세 정보 조회에 성공했습니다.",
                            content = @Content(
                                    schema = @Schema(implementation = CurationGetDetailRes.class),
                                    examples = @ExampleObject(value = """
                                            {
                                              "curationId": 123,
                                              "images": [
                                                {
                                                  "imageUrl": "https://example.com/curation/image1.jpg",
                                                  "sortOrder": 1
                                                },
                                                {
                                                  "imageUrl": "https://example.com/curation/image2.jpg",
                                                  "sortOrder": 2
                                                }
                                              ],
                                              "tags": [
                                                {
                                                  "name": "데이트하기 좋은"
                                                },
                                                {
                                                  "name": "분위기 좋은"
                                                }
                                              ],
                                              "title": "서울 분위기 맛집 카페 추천",
                                              "content": "연인들과 즐기기 좋은 분위기 맛집 카페를 소개합니다.",
                                              "reportCount": 1,
                                              "likeCount": 57,
                                              "isUserLiked": true
                                            }
                                            """)
                            )
                    ),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청입니다.", content = @Content),
                    @ApiResponse(responseCode = "401", description = "액세스 토큰의 형식이 올바르지 않습니다. Bearer 타입을 확인해 주세요.", content = @Content),
                    @ApiResponse(responseCode = "401", description = "액세스 토큰의 값이 올바르지 않습니다.", content = @Content),
                    @ApiResponse(responseCode = "401", description = "액세스 토큰이 만료되었습니다. 재발급 받아주세요.", content = @Content),
                    @ApiResponse(responseCode = "405", description = "잘못된 HTTP method 요청입니다.", content = @Content),
                    @ApiResponse(responseCode = "500", description = "서버 내부 오류입니다.", content = @Content)})
    ResponseEntity<CurationGetDetailRes> getCurationDetail(
            @Parameter(hidden = true) @UserId Long userId,
            @PathVariable("curationId") Long curationId);

    @Operation(
            summary = "큐레이션 좋아요 등록 API",
            responses = {
                    @ApiResponse(responseCode = "200", description = "큐레이션에 좋아요가 등록되었습니다.", content = @Content),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청입니다.", content = @Content),
                    @ApiResponse(responseCode = "401", description = "액세스 토큰의 형식이 올바르지 않습니다. Bearer 타입을 확인해 주세요.", content = @Content),
                    @ApiResponse(responseCode = "401", description = "액세스 토큰의 값이 올바르지 않습니다.", content = @Content),
                    @ApiResponse(responseCode = "401", description = "액세스 토큰이 만료되었습니다. 재발급 받아주세요.", content = @Content),
                    @ApiResponse(responseCode = "405", description = "잘못된 HTTP method 요청입니다.", content = @Content),
                    @ApiResponse(responseCode = "500", description = "서버 내부 오류입니다.", content = @Content)})
    ResponseEntity<Void> createCurationLike(@Parameter(hidden = true) @UserId Long userId,
                                            @PathVariable("curationId") Long curationId);

    @Operation(
            summary = "큐레이션 좋아요 해제 API",
            responses = {
                    @ApiResponse(responseCode = "200", description = "큐레이션에 좋아요가 해제되었습니다.", content = @Content),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청입니다.", content = @Content),
                    @ApiResponse(responseCode = "401", description = "액세스 토큰의 형식이 올바르지 않습니다. Bearer 타입을 확인해 주세요.", content = @Content),
                    @ApiResponse(responseCode = "401", description = "액세스 토큰의 값이 올바르지 않습니다.", content = @Content),
                    @ApiResponse(responseCode = "401", description = "액세스 토큰이 만료되었습니다. 재발급 받아주세요.", content = @Content),
                    @ApiResponse(responseCode = "405", description = "잘못된 HTTP method 요청입니다.", content = @Content),
                    @ApiResponse(responseCode = "500", description = "서버 내부 오류입니다.", content = @Content)})
    ResponseEntity<Void> deleteCurationLike(@Parameter(hidden = true) @UserId Long userId,
                                            @PathVariable("curationId") Long curationId);

    @Operation(
            summary = "큐레이션 삭제 API",
            responses = {
                    @ApiResponse(responseCode = "200", description = "큐레이션 삭제에 성공하였습니다.", content = @Content),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청입니다.", content = @Content),
                    @ApiResponse(responseCode = "401", description = "액세스 토큰의 형식이 올바르지 않습니다. Bearer 타입을 확인해 주세요.", content = @Content),
                    @ApiResponse(responseCode = "401", description = "액세스 토큰의 값이 올바르지 않습니다.", content = @Content),
                    @ApiResponse(responseCode = "401", description = "액세스 토큰이 만료되었습니다. 재발급 받아주세요.", content = @Content),
                    @ApiResponse(responseCode = "405", description = "잘못된 HTTP method 요청입니다.", content = @Content),
                    @ApiResponse(responseCode = "500", description = "서버 내부 오류입니다.", content = @Content)})
    ResponseEntity<Void> deleteCuration(@Parameter(hidden = true) @UserId Long userId,
                                            @PathVariable("curationId") Long curationId);
}
