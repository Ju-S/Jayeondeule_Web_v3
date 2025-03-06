package com.jayeondeule.smartfarm.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SensorIntervalUpdateDTO {
    private Long hous_id;
    private String snsr_rfrs_itvl;  // 센서 측정 시간 간격
}
