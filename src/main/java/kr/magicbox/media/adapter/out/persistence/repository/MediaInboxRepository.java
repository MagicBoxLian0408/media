package kr.magicbox.media.adapter.out.persistence.repository;

import kr.magicbox.media.adapter.out.persistence.entity.MediaInboxEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MediaInboxRepository extends JpaRepository<MediaInboxEntity, Long> {
    boolean existsByKey(String key);
    Optional<MediaInboxEntity> findByTopicAndPartitionAndOffset(String topic, Integer partition, Long offset);
}
