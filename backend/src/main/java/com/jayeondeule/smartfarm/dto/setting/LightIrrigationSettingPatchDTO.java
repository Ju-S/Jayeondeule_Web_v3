package com.jayeondeule.smartfarm.dto.setting;

import com.jayeondeule.smartfarm.enums.setting.LightIrrigationSettingType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
public class LightIrrigationSettingPatchDTO {
    private boolean dlteYn; // 관수, 조명 삭제 상태

    private LocalTime strtTime; // 관수, 조명 시작 시간
    private LocalTime fnshTime; // 관수, 조명 종료 시간
}
