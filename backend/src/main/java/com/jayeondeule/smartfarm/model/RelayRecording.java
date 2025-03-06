package com.jayeondeule.smartfarm.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "RELAY_L_RECORDING")
public class RelayRecording {
    //RELAY_L_RECORDING 테이블 엔티티 (릴레이 동작상태, 저장일자 등)

    @ManyToOne
    @JoinColumn(name = "farm_id", nullable = false)
    private Farm farm; // 릴레이가 속한 농장

    @ManyToOne
    @JoinColumn(name = "hous_id", nullable = false)
    private FarmHouse hous; // 릴레이가 속한 재배사

    @Column(nullable = false)
    private Boolean relay_1st_flag; // 릴레이1 작동 여부

    @Column(nullable = false)
    private Boolean relay_2st_flag; // 릴레이2 작동 여부

    @Column(nullable = false)
    private Boolean relay_3st_flag; // 릴레이3 작동 여부

    @Column(nullable = false)
    private Boolean relay_4st_flag; // 릴레이4 작동 여부

    @Column(nullable = false)
    private Boolean relay_5st_flag; // 릴레이5 작동 여부

    @Column(nullable = false)
    private Boolean relay_6st_flag; // 릴레이6 작동 여부

    @Column(nullable = false)
    private Boolean relay_7st_flag; // 릴레이7 작동 여부

    @Column(nullable = false)
    private Boolean relay_8st_flag; // 릴레이8 작동 여부

    @Column(nullable = false)
    private Boolean relay_9st_flag; // 릴레이9 작동 여부

    @Column(nullable = false)
    private Boolean relay_10st_flag; // 릴레이10 작동 여부

    @Column(nullable = false)
    private Boolean relay_11st_flag; // 릴레이11 작동 여부

    @Column(nullable = false)
    private Boolean relay_12st_flag; // 릴레이12 작동 여부

    @Column(nullable = false)
    private Boolean relay_13st_flag; // 릴레이13 작동 여부

    @Column(nullable = false)
    private Boolean relay_14st_flag; // 릴레이14 작동 여부

    @Column(nullable = false)
    private Boolean relay_15st_flag; // 릴레이15 작동 여부

    @Column(nullable = false)
    private Boolean relay_16st_flag; // 릴레이16 작동 여부

    @Column(nullable = false)
    private LocalDateTime recd_dttm = LocalDateTime.now();; // 측정일자
}
