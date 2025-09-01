package com.jayeondeule.smartfarm.dto.setting;

import com.jayeondeule.smartfarm.entity.house.FarmHouse;
import com.jayeondeule.smartfarm.enums.setting.LightIrrigationSettingType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
public class LightIrrigationSettingDTO {
    private LocalDateTime setnDttm; // 설정일자

    private boolean dlteYn; // toggle 여부
    private LightIrrigationSettingType unitType; // 관수, 조명 타입 설정

    private LocalTime strtTime; // 관수, 조명 시작 시간
    private LocalTime fnshTime; // 관수, 조명 종료 시간
}
