package com.jayeondeule.smartfarm.entity.sensor;

import com.jayeondeule.smartfarm.entity.house.FarmHouse;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "SENSOR_L_RECORDING")
public class SensorRecording {
    //SENSOR_L_RECORDING 테이블 엔티티 (측정일자, 온도, 습도 등)
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sensor_seq_gen"
    )
    @SequenceGenerator(
            name = "sensor_seq_gen",
            sequenceName = "sensor_seq",
            allocationSize = 1,
            initialValue = 0
    )
    private long id;

    @ManyToOne
    @JoinColumn(name = "hous_id", nullable = false)
    private FarmHouse hous; // 센서가 속한 재배사

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
    private boolean lightLvelValu = false; // 조명 작동 상태

    @Column
    private boolean watrLvelValu = false; // 관수 작동 상태

    @Column(nullable = false)
    private LocalDateTime recdDttm = LocalDateTime.now(); // 측정일자
}
