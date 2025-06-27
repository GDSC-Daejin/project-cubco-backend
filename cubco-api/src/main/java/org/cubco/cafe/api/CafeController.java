package org.cubco.cafe.api;

import lombok.RequiredArgsConstructor;
import org.cubco.auth.resolver.UserId;
import org.cubco.cafe.dto.CafeSimpleRes;
import org.cubco.cafe.service.CafeService;
import org.cubco.response.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cafes")
@PreAuthorize("hasRole('MANAGER')")
public class CafeController {
    private final CafeService cafeService;

    @GetMapping("/mine")
    public CommonResponse<List<CafeSimpleRes>> getMyCafes(@UserId Long managerId) {
        List<CafeSimpleRes> response = cafeService.getCafesByManager(managerId);
        return CommonResponse.successWithData(HttpStatus.OK, response);
    }
}
