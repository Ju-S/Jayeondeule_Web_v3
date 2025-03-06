package com.jayeondeule.smartfarm.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UpdateLightIrrigationSettingDTO {
    private Long hous_id; // 설정이 속한 재배사

    private String unit_type; // 관수, 조명 타입 설정

    private LocalDateTime strt_time; // 관수, 조명 시작 시간
    private LocalDateTime fnsh_time; // 관수, 조명 종료 시간

    private LocalDateTime setn_dttm = LocalDateTime.now(); // 설정일자
}
