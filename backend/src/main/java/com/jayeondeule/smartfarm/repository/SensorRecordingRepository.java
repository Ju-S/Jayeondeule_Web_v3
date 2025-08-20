package com.jayeondeule.smartfarm.repository;

import com.jayeondeule.smartfarm.entity.sensor.SensorRecording;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRecordingRepository extends JpaRepository<SensorRecording, Long> {
    //센서 데이터 접근 인터페이스
}
