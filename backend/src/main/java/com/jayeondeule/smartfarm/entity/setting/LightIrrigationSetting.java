package com.jayeondeule.smartfarm.entity.setting;

import com.jayeondeule.smartfarm.entity.house.FarmHouse;
import com.jayeondeule.smartfarm.enums.setting.LightIrrigationSettingType;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@IdClass(LightIrrigationSettingId.class)
@Table(name = "LIGHT_IRRIGATION_S_SETTING")
public class LightIrrigationSetting {
    //LIGHT_IRRIGATION_S_SETTING 테이블 (관수, 조명 시간 설정)
    @Id
    private long farmId;

    @Id
    private long housId; // 설정이 속한 재배사

    @Id
    @Column(columnDefinition = "TIMESTAMP(6) WITHOUT TIME ZONE")
    private LocalDateTime setnDttm = LocalDateTime.now(); // 설정일자

    @Column(nullable = false)
    private boolean enable = true; // 설정 적용 여부

    @Column(nullable = false)
    private boolean dlteYn = false; // 설정 삭제 상태

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LightIrrigationSettingType unitType; // 관수, 조명 타입 설정

    @Column(nullable = false)
    private LocalDateTime strtTime; // 관수, 조명 시작 시간

    @Column(nullable = false)
    private LocalDateTime fnshTime; // 관수, 조명 종료 시간
}
