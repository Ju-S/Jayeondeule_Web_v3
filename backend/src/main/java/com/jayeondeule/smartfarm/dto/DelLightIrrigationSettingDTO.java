package com.jayeondeule.smartfarm.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DelLightIrrigationSettingDTO {
    private Long hous_id; // 설정이 속한 재배사
    private Boolean dlte_yn; // 설정 삭제 여부
}
