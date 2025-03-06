package com.jayeondeule.smartfarm.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "SENSOR_L_RECORDING")
public class SensorRecording {
    //SENSOR_L_RECORDING 테이블 엔티티 (측정일자, 온도, 습도 등)

    @ManyToOne
    @JoinColumn(name = "farm_id", nullable = false)
    private Farm farm; // 센서가 속한 농장

    @ManyToOne
    @JoinColumn(name = "hous_id", nullable = false)
    private FarmHouse hous; // 센서가 속한 재배사

    @Column
    private Double indr_tprt_valu; // 실내 온도

    @Column
    private Double indr_hmdt_valu; // 실내 습도

    @Column
    private Double oudr_tprt_valu; // 외부 온도

    @Column
    private Double oudr_hmdt_valu; // 외부 습도

    @Column
    private Double co2_valu; // 실내 co2

    @Column
    private Double watr_tprt_valu; // 수온

    @Column
    private Boolean light_lvel_valu; // 조명 작동 상태

    @Column
    private Boolean watr_lvel_valu; // 관수 작동 상태

    @Column(nullable = false)
    private LocalDateTime recd_dttm = LocalDateTime.now(); // 측정일자
}
