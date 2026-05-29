package kr.magicbox.media.adapter.in.web.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import kr.magicbox.media.application.dto.command.GenerateUploadUrlCommand;
import kr.magicbox.media.domain.vo.UserId;

public record GenerateUploadUrlRequest(
        @NotBlank(message = "파일명은 필수입니다.") String fileName,
        @NotBlank(message = "Content-Type은 필수입니다.") String contentType,
        @NotNull(message = "파일 크기는 필수입니다.") @Positive(message = "파일 크기는 0보다 커야 합니다.") Long fileSize
) {
    public GenerateUploadUrlCommand toCommand(UserId uploaderId) {
        return GenerateUploadUrlCommand.builder()
                .uploaderId(uploaderId)
                .fileName(fileName)
                .contentType(contentType)
                .fileSize(fileSize)
                .build();
    }
}
