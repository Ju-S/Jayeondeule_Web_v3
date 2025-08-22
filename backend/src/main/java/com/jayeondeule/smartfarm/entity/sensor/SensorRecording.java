package com.jayeondeule.smartfarm.entity.sensor;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@IdClass(SensorRecordingId.class)
@Table(name = "SENSOR_L_RECORDING")
public class SensorRecording {
    //SENSOR_L_RECORDING 테이블 엔티티 (측정일자, 온도, 습도 등)
    @Id
    private long farmId;

    @Id
    private long housId; // 센서가 속한 재배사

    @Id
    @Column(columnDefinition = "TIMESTAMP(6) WITHOUT TIME ZONE")
    private LocalDateTime recdDttm = LocalDateTime.now(); // 측정일자

    @Column
    private double indrTprtValu = 0; // 실내 온도

    @Column
    private double indrHmdtValu = 0; // 실내 습도

    @Column
    private double oudrTprtValu = 0; // 외부 온도

    @Column
    private double oudrHmdtValu = 0; // 외부 습도

    @Column
    private double co2Valu = 0; // 실내 co2

    @Column
    private double watrTprtValu = 0; // 수온

    @Column
    private double lightLvelValu = 0; // 조명 작동 상태

    @Column
    private double watrLvelValu = 0; // 관수 작동 상태
}
