package kr.magicbox.media.application.dto.command;

import kr.magicbox.media.domain.vo.UploaderId;
import lombok.Builder;

@Builder
public record GenerateUploadUrlCommand(
        UploaderId uploaderId,
        String fileName,
        String contentType
) {}
