package kr.magicbox.media.application.port.in;

import kr.magicbox.media.domain.vo.UploaderId;

public interface DeleteMediaUseCase {
    void delete(String uuid, UploaderId requesterId);
    void deleteAsAdmin(String uuid);
}
