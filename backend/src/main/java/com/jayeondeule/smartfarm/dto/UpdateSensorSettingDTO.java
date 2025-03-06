package com.jayeondeule.smartfarm.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UpdateSensorSettingDTO {
    //센서 설정 값 객체
    private Long hous_id; // 설정이 속한 재배사

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

    private LocalDateTime setn_dttm; // 설정일자
}
