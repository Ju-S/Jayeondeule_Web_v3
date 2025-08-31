package com.jayeondeule.smartfarm.entity.house;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    @Setter
    @Column(nullable = false)
    private String housName; // 재배사 이름

    @Setter
    @Column
    private LocalDateTime lastGetDttm; // 마지막 작물 수확 일자

    @Setter
    @Column
    private String snsrRfrsItvl;  // 센서 측정 시간 간격

    @Setter
    @Column(nullable = false)
    private boolean rfrsFlag = false; // 수동 센서 측정

    @Setter
    @Column(nullable = false)
    private String cropKind; // 작물 종류

    @Setter
    @Column(nullable = false)
    private boolean mnulCtrlFlag = false; // 릴레이 수동 조작 여부

    @Column(nullable = false, columnDefinition = "TIMESTAMP(6) WITHOUT TIME ZONE")
    private LocalDateTime rgstDttm = LocalDateTime.now(); // 등록일자
}
