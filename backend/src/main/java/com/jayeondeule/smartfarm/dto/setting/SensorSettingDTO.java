package com.jayeondeule.smartfarm.dto.setting;

import com.jayeondeule.smartfarm.entity.farm.Farm;
import com.jayeondeule.smartfarm.entity.house.FarmHouse;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SensorSettingDTO {
    private LocalDateTime setnDttm; // 설정일자

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
