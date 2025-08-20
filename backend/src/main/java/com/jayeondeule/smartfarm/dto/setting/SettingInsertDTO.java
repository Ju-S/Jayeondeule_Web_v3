package com.jayeondeule.smartfarm.dto.setting;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SettingInsertDTO {
    private LightIrrigationSettingInsertDTO lightIrrigationSettingInsertDTO;
    private SensorSettingInsertDTO sensorSettingPatchDTO;
}
