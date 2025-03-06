package com.jayeondeule.smartfarm.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "FARMHOUSE_M_INFO")
public class FarmHouse {
    //FARMHOUSE_M_INFO 테이블에 대응하는 엔티티

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hous_id;

    @ManyToOne
    @JoinColumn(name = "farm_id", nullable = false)
    private Farm farm; // 농장과 관계 설정

    @Column(nullable = false, unique = true)
    private String hous_name; // 재배사 이름

    @Column
    private LocalDateTime last_get_dttm; // 마지막 작물 수확 일자

    @Column(nullable = false)
    private String snsr_rfrs_itvl;  // 센서 측정 시간 간격

    @Column(nullable = false)
    private String rfrs_flag; // 수동 센서 측정

    @Column(nullable = false)
    private String crop_kind; // 작물 종류

    @Column(nullable = false)
    private Boolean mnul_ctrl_flag; // 릴레이 수동 조작 여부

    @Column(nullable = false)
    private LocalDateTime rgst_dttm = LocalDateTime.now(); // 등록일자
}
