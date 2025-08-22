package com.jayeondeule.smartfarm.dto.setting;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SensorSettingInsertDTO {
    private double tprtMin;
    private double tprtMax;

    private double hmdtMin;
    private double hmdtMax;

    private double co2Min;
    private double co2Max;

    private double watrTprtMin;
    private double watrTprtMax;

    private double heatTprtMin;
    private double heatTprtMax;
}
