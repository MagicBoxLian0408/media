package kr.magicbox.media.domain.vo;

import kr.magicbox.media.domain.exception.InvalidFieldException;

public record UploaderId(Long value) {

    public UploaderId {
        if (value == null || value <= 0) throw new InvalidFieldException("업로더 ID는 양수여야 합니다.");
    }

    public static UploaderId of(Long value) {
        return new UploaderId(value);
    }
}
