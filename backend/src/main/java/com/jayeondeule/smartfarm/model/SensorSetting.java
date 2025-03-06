package com.jayeondeule.smartfarm.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "SENSOR_M_SETTING")
public class SensorSetting {
    //SENSOR_M_SETTING 테이블 (온도, 습도, co2 등 설정 값)

    @ManyToOne
    @JoinColumn(name = "farm_id", nullable = false)
    private Farm farm; // 설정이 속한 농장

    @ManyToOne
    @JoinColumn(name = "hous_id", nullable = false)
    private FarmHouse hous; // 설정이 속한 재배사

    @Column(nullable = false)
    private Integer tprt_min = 0;

    @Column(nullable = false)
    private Integer tprt_max = 0;

    @Column(nullable = false)
    private Integer hmdt_min = 0;

    @Column(nullable = false)
    private Integer hmdt_max = 0;

    @Column(nullable = false)
    private Integer co2_min = 0;

    @Column(nullable = false)
    private Integer co2_max = 0;

    @Column(nullable = false)
    private Integer watr_tprt_min = 0;

    @Column(nullable = false)
    private Integer watr_tprt_max = 0;

    @Column(nullable = false)
    private Integer heat_tprt_min = 0;

    @Column(nullable = false)
    private Integer heat_tprt_max = 0;

    @Column(nullable = false)
    private LocalDateTime setn_dttm = LocalDateTime.now(); // 설정일자
}
