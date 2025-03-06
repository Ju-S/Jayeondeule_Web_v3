package com.jayeondeule.smartfarm.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "FARMHOUSE_L_MEMO")
public class FarmHouseMemoRecording {
    //FARMHOUSE_L_MEMO 각 재배사의 메모 테이블에 대응하는 엔티티

    @ManyToOne
    @JoinColumn(name = "farm_id", nullable = false)
    private Farm farm; // 메모가 속한 농장

    @ManyToOne
    @JoinColumn(name = "hous_id", nullable = false)
    private FarmHouse hous; // 메모가 속한 재배사

    @Column
    private String memo; // 메모내용

    @Column
    private String author; // 작성자

    @Column(nullable = false)
    private LocalDateTime recd_dttm = LocalDateTime.now(); // 기록일자
}
