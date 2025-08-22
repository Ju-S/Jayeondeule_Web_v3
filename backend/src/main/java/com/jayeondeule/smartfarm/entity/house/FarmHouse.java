package com.jayeondeule.smartfarm.entity.house;

import com.jayeondeule.smartfarm.entity.farm.Farm;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@IdClass(FarmHouseId.class)
@Table(name = "FARMHOUSE_M_INFO")
public class FarmHouse {
    //FARMHOUSE_M_INFO 테이블에 대응하는 엔티티
    @Id
    private long farmId; // 농장과 관계 설정

    @Id
    private long housId;

    @Column(nullable = false, unique = true)
    private String housName; // 재배사 이름

    @Column
    private LocalDateTime lastGetDttm; // 마지막 작물 수확 일자

    @Column
    private String snsrRfrsItvl;  // 센서 측정 시간 간격

    @Column(nullable = false)
    private boolean rfrsFlag = false; // 수동 센서 측정

    @Column(nullable = false)
    private String cropKind; // 작물 종류

    @Column(nullable = false)
    private boolean mnulCtrlFlag = false; // 릴레이 수동 조작 여부

    @Column(nullable = false, columnDefinition = "TIMESTAMP(6) WITHOUT TIME ZONE")
    private LocalDateTime rgstDttm = LocalDateTime.now(); // 등록일자
}
