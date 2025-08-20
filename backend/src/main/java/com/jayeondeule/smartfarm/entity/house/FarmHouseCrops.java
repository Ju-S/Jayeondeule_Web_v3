package com.jayeondeule.smartfarm.entity.house;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@Table(name = "FARMHOUSE_I_CROPS")
public class FarmHouseCrops {
    // FARMHOUSE_I_CROPS 테이블에 대응하는 엔티티
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "farmhous_crops_seq_gen"
    )
    @SequenceGenerator(
            name = "farmhous_crops_seq_gen",
            sequenceName = "farmhous_crops_seq",
            allocationSize = 1,
            initialValue = 0
    )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hous_id", nullable = false)
    private FarmHouse hous; // 작물이 속한 재배사

    @Column
    private int cropStat;

    @Column
    private int cropQtty;

    @Column
    private int cropGradeQtty1 = 0;

    @Column
    private int cropGradeQtty2 = 0;

    @Column
    private int cropGradeQtty3 = 0;

    @Column
    private int cropGradeQtty4 = 0;

    @Column
    private int cropGradeQtty5 = 0;

    @Column
    private int cropGradeAmut1 = 0;

    @Column
    private int cropGradeAmut2 = 0;

    @Column
    private int cropGradeAmut3 = 0;

    @Column
    private int cropGradeAmut4 = 0;

    @Column
    private int cropGradeAmut5 = 0;

    @Column
    private String rmks; // 설명

    @Column
    private List<String> oriFileImge;

    @Column
    private List<String> sysFileImge;

    @Column(nullable = false)
    private LocalDateTime cropStrtDate; // 작물 재배 시작 일자

    @Column
    private LocalDateTime cropEndDate; // 작물 재배 종료 일자

    @Column(nullable = false)
    private LocalDateTime recdDttm = LocalDateTime.now(); // 기록일자
}
