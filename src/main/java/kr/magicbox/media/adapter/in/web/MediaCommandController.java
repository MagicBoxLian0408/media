package kr.magicbox.media.adapter.in.web;

import jakarta.validation.Valid;
import kr.magicbox.media.adapter.in.web.dto.request.GenerateUploadUrlRequest;
import kr.magicbox.media.adapter.in.web.dto.response.GenerateUploadUrlResponse;
import kr.magicbox.media.application.port.in.GenerateUploadUrlUseCase;
import kr.magicbox.media.domain.vo.UploaderId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/media")
@RequiredArgsConstructor
public class MediaCommandController {

    private final GenerateUploadUrlUseCase generateUploadUrlUseCase;

    @PostMapping("/upload-url")
    public ResponseEntity<GenerateUploadUrlResponse> generateUploadUrl(
            @AuthenticationPrincipal UploaderId uploaderId,
            @Valid @RequestBody GenerateUploadUrlRequest request
    ) {
        return ResponseEntity.ok(
                GenerateUploadUrlResponse.from(generateUploadUrlUseCase.generateUploadUrl(request.toCommand(uploaderId)))
        );
    }
}
