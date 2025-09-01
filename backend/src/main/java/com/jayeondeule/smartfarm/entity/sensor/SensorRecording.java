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
    private Double indrTprtValu = 0d; // 실내 온도

    @Column
    private Double indrHmdtValu = 0d; // 실내 습도

    @Column
    private Double oudrTprtValu = 0d; // 외부 온도

    @Column
    private Double oudrHmdtValu = 0d; // 외부 습도

    @Column
    private Double co2Valu = 0d; // 실내 co2

    @Column
    private Double watrTprtValu = 0d; // 수온

    @Column
    private Double lightLvelValu = 0d; // 조명 작동 상태

    @Column
    private Double watrLvelValu = 0d; // 관수 작동 상태
}
