package com.jayeondeule.smartfarm.repository;

import com.jayeondeule.smartfarm.entity.sensor.SensorRecording;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SensorRecordingRepository extends JpaRepository<SensorRecording, Long> {
    List<SensorRecording> findByFarmIdAndHousIdAndRecdDttmBetween(long farmId, long housId, LocalDateTime recdDttmAfter, LocalDateTime recdDttmBefore);
}
