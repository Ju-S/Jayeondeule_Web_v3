package com.jayeondeule.smartfarm.entity.house;

import com.jayeondeule.smartfarm.entity.farm.Farm;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "FARMHOUSE_M_INFO")
public class FarmHouse {
    //FARMHOUSE_M_INFO 테이블에 대응하는 엔티티
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "farmhous_seq_gen"
    )
    @SequenceGenerator(
            name = "farmhous_seq_gen",
            sequenceName = "farmhous_seq",
            allocationSize = 1,
            initialValue = 0
    )
    private long housId;

    @ManyToOne
    @JoinColumn(name = "farm_id", nullable = false)
    private Farm farm; // 농장과 관계 설정

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
    private boolean mnulCtrlFlag; // 릴레이 수동 조작 여부

    @Column(nullable = false)
    private LocalDateTime rgstDttm = LocalDateTime.now(); // 등록일자
}
