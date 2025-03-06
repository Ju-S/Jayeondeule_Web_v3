package com.jayeondeule.smartfarm.dto;

import jakarta.persistence.Column;

import java.time.LocalDateTime;

public class SensorDataDTO {
    //센서 측정 데이터 전송 객체
    private Double indr_tprt_valu; // 실내 온도
    private Double indr_hmdt_valu; // 실내 습도
    private Double oudr_tprt_valu; // 외부 온도
    private Double oudr_hmdt_valu; // 외부 습도
    private Double co2_valu; // 실내 co2
    private Double watr_tprt_valu; // 수온
    private Boolean light_lvel_valu; // 조명 작동 상태
    private Boolean watr_lvel_valu; // 관수 작동 상태
    private LocalDateTime recd_dttm; // 측정일자
}
