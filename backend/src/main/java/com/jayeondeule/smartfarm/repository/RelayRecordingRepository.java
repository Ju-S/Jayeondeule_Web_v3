package com.jayeondeule.smartfarm.repository;

import com.jayeondeule.smartfarm.entity.relay.RelayRecording;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelayRecordingRepository extends JpaRepository<RelayRecording, Long> {
    //릴레이 데이터 접근 인터페이스
}
