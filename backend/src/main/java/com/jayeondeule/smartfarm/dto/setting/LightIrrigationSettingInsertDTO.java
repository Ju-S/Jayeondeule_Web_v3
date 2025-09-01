package com.jayeondeule.smartfarm.dto.setting;


import com.jayeondeule.smartfarm.entity.house.FarmHouse;
import com.jayeondeule.smartfarm.enums.setting.LightIrrigationSettingType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
public class LightIrrigationSettingInsertDTO {
    private LightIrrigationSettingType unitType; // 관수, 조명 타입 설정

    private LocalTime strtTime; // 관수, 조명 시작 시간
    private LocalTime fnshTime; // 관수, 조명 종료 시간
}
