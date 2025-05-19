package org.cubco.curation.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.cubco.auth.resolver.UserId;
import org.cubco.curation.dto.request.CurationCreateReq;
import org.cubco.curation.dto.response.CurationCreateRes;
import org.cubco.curation.dto.response.CurationGetDetailRes;
import org.cubco.curation.service.CurationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/curations")
@RequiredArgsConstructor
public class CurationController implements CurationApi {
    private final CurationService curationService;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CurationCreateRes> createCuration(@UserId Long userId,
                                                            @RequestPart("curation") @Valid CurationCreateReq curation,
                                                            @RequestPart("images") List<MultipartFile> images) {
        CurationCreateRes dateCreateRes = curationService.createCuration(userId, curation, images);
        return ResponseEntity.status(HttpStatus.CREATED).body(dateCreateRes);
    }

    @PostMapping("/{curationId}/likes")
    public ResponseEntity<Void> createCurationLike(@UserId Long userId, @PathVariable("curationId") Long curationId) {
        curationService.createCurationLike(userId, curationId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{curationId}/likes")
    public ResponseEntity<Void> deleteCurationLike(@UserId Long userId, @PathVariable("curationId") Long curationId) {
        curationService.deleteCurationLike(userId, curationId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{curationId}")
    public ResponseEntity<CurationGetDetailRes> getCurationDetail(@UserId Long userId, @PathVariable("curationId") Long curationId) {
        CurationGetDetailRes curationGetDetailRes = curationService.getCurationDetail(userId, curationId);
        return ResponseEntity.status(HttpStatus.OK).body(curationGetDetailRes);
    }
}
