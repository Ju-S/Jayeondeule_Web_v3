package com.jayeondeule.smartfarm.entity.setting;

import com.jayeondeule.smartfarm.entity.farm.Farm;
import com.jayeondeule.smartfarm.entity.house.FarmHouse;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Entity
@IdClass(SensorSettingId.class)
@Table(name = "SENSOR_M_SETTING")
public class SensorSetting {
    //SENSORM_SETTING 테이블 (온도, 습도, co2 등 설정 값)
    @Setter
    @Id
    private long farmId;

    @Setter
    @Id
    private long housId; // 설정이 속한 재배사

    @Id
    @Column(columnDefinition = "TIMESTAMP(6) WITHOUT TIME ZONE")
    private LocalDateTime setnDttm = LocalDateTime.now(); // 설정일자

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
}
