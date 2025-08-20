package com.jayeondeule.smartfarm.entity.farm;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "FARM_M_INFO")
public class Farm {
    //FARM_M_INFO 테이블에 대응하는 엔티티
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "farm_seq_gen"
    )
    @SequenceGenerator(
            name = "farm_seq_gen",
            sequenceName = "farm_seq",
            allocationSize = 1,
            initialValue = 0
    )
    private long farmId;

    @Column(nullable = false, unique = true)
    private String farmName; // 농장 이름

    @Column(nullable = false, unique = true)
    private String farmDomi; // 농장 도메인

    @Column(nullable = false)
    private LocalDateTime openDate; // 개업일자

    @Column
    private LocalDateTime closeDate; // 폐업일자 (선택적)

    @Column
    private String telNo; // 대표번호

    @Column
    private String hpNo; // 전화번호

    @Column
    private String faxNo; // 팩스번호

    @Column
    private String email; // 대표메일

    @Column(nullable = false)
    private String ipAddr; // 농장 IP 정보

    @Column(nullable = false)
    private String port; // 포트 정보

    private String addr; // 주소

    private String mainPrdt; // 주요 작물

    @Column(columnDefinition = "TEXT")
    private String rmks; // 농장 설명

    @Column(nullable = false)
    private LocalDateTime rgstDttm = LocalDateTime.now(); // 등록일자
}
