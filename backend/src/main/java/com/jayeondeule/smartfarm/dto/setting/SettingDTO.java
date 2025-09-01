package com.jayeondeule.smartfarm.dto.setting;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SettingDTO {
    private List<LightIrrigationSettingDTO> lightIrrigationSettingDTO;
    private SensorSettingDTO sensorSettingDTO;
}
