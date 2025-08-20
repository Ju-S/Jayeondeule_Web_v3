package com.jayeondeule.smartfarm.entity.setting;

import com.jayeondeule.smartfarm.entity.house.FarmHouse;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "LIGHT_IRRIGATION_S_SETTING")
public class LightIrrigationSetting {
    //LIGHT_IRRIGATION_S_SETTING 테이블 (관수, 조명 시간 설정)
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "lightIrrigation_seq_gen"
    )
    @SequenceGenerator(
            name = "lightIrrigation_seq_gen",
            sequenceName = "lightIrrigation_seq",
            allocationSize = 1,
            initialValue = 0
    )
    private long id;

    @ManyToOne
    @JoinColumn(name = "hous_id", nullable = false)
    private FarmHouse hous; // 설정이 속한 재배사

    @Column(nullable = false)
    private boolean dlteYn = false; // 설정 삭제 여부

    @Column(nullable = false)
    private String unitType; // 관수, 조명 타입 설정

    @Column(nullable = false)
    private LocalDateTime strtTime; // 관수, 조명 시작 시간

    @Column(nullable = false)
    private LocalDateTime fnshTime; // 관수, 조명 종료 시간

    @Column(nullable = false)
    private LocalDateTime setnDttm = LocalDateTime.now(); // 설정일자
}
