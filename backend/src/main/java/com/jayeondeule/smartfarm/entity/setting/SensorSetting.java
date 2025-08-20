package com.jayeondeule.smartfarm.entity.setting;

import com.jayeondeule.smartfarm.entity.farm.Farm;
import com.jayeondeule.smartfarm.entity.house.FarmHouse;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "SENSORM_SETTING")
public class SensorSetting {
    //SENSORM_SETTING 테이블 (온도, 습도, co2 등 설정 값)

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "setting_seq_gen"
    )
    @SequenceGenerator(
            name = "setting_seq_gen",
            sequenceName = "setting_seq",
            allocationSize = 1,
            initialValue = 0
    )
    private long id;

    @ManyToOne
    @JoinColumn(name = "farm_id", nullable = false)
    private Farm farm; // 설정이 속한 농장

    @ManyToOne
    @JoinColumn(name = "hous_id", nullable = false)
    private FarmHouse hous; // 설정이 속한 재배사

    @Column(nullable = false)
    private double tprtMin = 0;

    @Column(nullable = false)
    private double tprtMax = 0;

    @Column(nullable = false)
    private double hmdtMin = 0;

    @Column(nullable = false)
    private double hmdtMax = 0;

    @Column(nullable = false)
    private double co2Min = 0;

    @Column(nullable = false)
    private double co2Max = 0;

    @Column(nullable = false)
    private double watrTprtMin = 0;

    @Column(nullable = false)
    private double watrTprtMax = 0;

    @Column(nullable = false)
    private double heatTprtMin = 0;

    @Column(nullable = false)
    private double heatTprtMax = 0;

    @Column(nullable = false)
    private LocalDateTime setnDttm = LocalDateTime.now(); // 설정일자
}
