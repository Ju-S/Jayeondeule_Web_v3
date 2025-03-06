package com.jayeondeule.smartfarm.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "FARMHOUSE_I_CROPS")
public class FarmHouseCrops {
    // FARMHOUSE_I_CROPS 테이블에 대응하는 엔티티

    @ManyToOne
    @JoinColumn(name = "farm_id", nullable = false)
    private Farm farm; // 작물이 속한 농장

    @ManyToOne
    @JoinColumn(name = "hous_id", nullable = false)
    private FarmHouse hous; // 작물이 속한 재배사

    @Column
    private Integer crop_stat;

    @Column
    private Integer crop_qtty;

    @Column
    private Integer crop_grade_qtty_1 = 0;

    @Column
    private Integer crop_grade_qtty_2 = 0;

    @Column
    private Integer crop_grade_qtty_3 = 0;

    @Column
    private Integer crop_grade_qtty_4 = 0;

    @Column
    private Integer crop_grade_qtty_5 = 0;

    @Column
    private Integer crop_grade_amut_1 = 0;

    @Column
    private Integer crop_grade_amut_2 = 0;

    @Column
    private Integer crop_grade_amut_3 = 0;

    @Column
    private Integer crop_grade_amut_4 = 0;

    @Column
    private Integer crop_grade_amut_5 = 0;

    @Column
    private String rmks; // 설명

    @Column
    private String file_name;

    @Column
    private Integer file_size;

    @Column
    private String file_imge;

    @Column(nullable = false)
    private LocalDateTime crop_strt_date; // 작물 재배 시작 일자

    @Column
    private LocalDateTime crop_end_date; // 작물 재배 종료 일자

    @Column(nullable = false)
    private LocalDateTime recd_dttm = LocalDateTime.now(); // 기록일자
}
