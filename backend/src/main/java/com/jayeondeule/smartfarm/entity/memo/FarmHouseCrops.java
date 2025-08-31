package com.jayeondeule.smartfarm.entity.memo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Entity
@IdClass(FarmHouseCropsId.class)
@Table(name = "FARMHOUSE_I_CROPS")
public class FarmHouseCrops {
    // FARMHOUSE_I_CROPS 테이블에 대응하는 엔티티

    @Setter
    @Id
    private long farmId;

    @Setter
    @Id
    private long housId; // 작물이 속한 재배사

    @Id
    @Column(columnDefinition = "TIMESTAMP(6) WITHOUT TIME ZONE")
    private LocalDateTime recdDttm = LocalDateTime.now(); // 기록일자

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

    @Setter
    @Column
    private String athr; // 작성자

    @Setter
    @Column
    private String rmks; // 설명

    @Column
    private String orgn; // 업로드 이미지 파일명

    @Column
    private String sstm; // 업로드 이미지 실제 경로

    @Column
    private LocalDateTime cropStrtDate; // 작물 재배 시작 일자

    @Column
    private LocalDateTime cropEndDate; // 작물 재배 종료 일자
}
