package com.jayeondeule.smartfarm.entity.setting;

import com.jayeondeule.smartfarm.enums.setting.LightIrrigationSettingType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Entity
@IdClass(LightIrrigationSettingId.class)
@Table(name = "LIGHT_IRRIGATION_S_SETTING")
public class LightIrrigationSetting {
    //LIGHT_IRRIGATION_S_SETTING 테이블 (관수, 조명 시간 설정)
    @Setter
    @Id
    private long farmId;

    @Setter
    @Id
    private long housId; // 설정이 속한 재배사

    @Id
    @Column(columnDefinition = "TIMESTAMP(6) WITHOUT TIME ZONE")
    private LocalDateTime setnDttm = LocalDateTime.now(); // 설정일자

    @Setter
    @Column(nullable = false)
    private boolean dlteYn = false; // 설정 삭제 상태

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LightIrrigationSettingType unitType; // 관수, 조명 타입 설정

    @Setter
    @Column(nullable = false)
    private LocalTime strtTime; // 관수, 조명 시작 시간

    @Setter
    @Column(nullable = false)
    private LocalTime fnshTime; // 관수, 조명 종료 시간
}
