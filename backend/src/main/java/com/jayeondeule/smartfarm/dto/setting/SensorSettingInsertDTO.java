package com.jayeondeule.smartfarm.dto.setting;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SensorSettingInsertDTO {
    //센서 설정 값 객체
    private Integer tprt_min = 0;
    private Integer tprt_max = 0;
    private Integer hmdt_min = 0;
    private Integer hmdt_max = 0;
    private Integer co2_min = 0;
    private Integer co2_max = 0;
    private Integer watr_tprt_min = 0;
    private Integer watr_tprt_max = 0;
    private Integer heat_tprt_min = 0;
    private Integer heat_tprt_max = 0;

    private LocalDateTime setn_dttm = LocalDateTime.now(); // 설정일자
}
