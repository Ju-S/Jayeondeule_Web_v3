package com.jayeondeule.smartfarm.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "LIGHT_IRRIGATION_S_SETTING")
public class LightIrrigationSetting {
    //LIGHT_IRRIGATION_S_SETTING 테이블 (관수, 조명 시간 설정)

    @ManyToOne
    @JoinColumn(name = "farm_id", nullable = false)
    private Farm farm; // 설정이 속한 농장

    @ManyToOne
    @JoinColumn(name = "hous_id", nullable = false)
    private FarmHouse hous; // 설정이 속한 재배사

    @Column(nullable = false)
    private Boolean dlte_yn = false; // 설정 삭제 여부

    @Column(nullable = false)
    private String unit_type; // 관수, 조명 타입 설정

    @Column(nullable = false)
    private LocalDateTime strt_time; // 관수, 조명 시작 시간

    @Column(nullable = false)
    private LocalDateTime fnsh_time; // 관수, 조명 종료 시간

    @Column(nullable = false)
    private LocalDateTime setn_dttm = LocalDateTime.now(); // 설정일자
}
