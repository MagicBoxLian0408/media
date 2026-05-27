package kr.magicbox.media.adapter.out.storage;

import kr.magicbox.media.application.port.out.ObjectStoragePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.time.Duration;

@Slf4j
@Component
@RequiredArgsConstructor
public class MinioStorageAdapter implements ObjectStoragePort {

    private final S3Client s3Client;
    private final S3Presigner s3Presigner;
    private final MinioProperties minioProperties;

    @Override
    public String generatePresignedPutUrl(String uuid, String contentType, long ttlSeconds) {
        PutObjectRequest objectRequest = PutObjectRequest.builder()
                .bucket(minioProperties.getBucket())
                .key(uuid)
                .contentType(contentType)
                .build();

        PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
                .signatureDuration(Duration.ofSeconds(ttlSeconds))
                .putObjectRequest(objectRequest)
                .build();

        PresignedPutObjectRequest presignedRequest = s3Presigner.presignPutObject(presignRequest);
        return presignedRequest.url().toString();
    }

    @Override
    public void delete(String uuid) {
        s3Client.deleteObject(DeleteObjectRequest.builder()
                .bucket(minioProperties.getBucket())
                .key(uuid)
                .build());
        log.debug("[MinIO] 파일 삭제 완료. uuid={}", uuid);
    }

    @Override
    public void deleteIfExists(String uuid) {
        try {
            delete(uuid);
        } catch (NoSuchKeyException e) {
            log.debug("[MinIO] 파일 없음 (무시). uuid={}", uuid);
        }
    }
}
