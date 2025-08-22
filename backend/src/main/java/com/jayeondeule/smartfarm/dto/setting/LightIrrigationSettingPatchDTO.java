package com.jayeondeule.smartfarm.dto.setting;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class LightIrrigationSettingPatchDTO {
    private String unitType; // 관수, 조명 타입 설정

    private boolean enable; // 관수, 조명 설정 적용 여부
    private boolean dlteYn; // 관수, 조명 삭제 상태

    private LocalDateTime strtTime; // 관수, 조명 시작 시간
    private LocalDateTime fnshTime; // 관수, 조명 종료 시간
}
