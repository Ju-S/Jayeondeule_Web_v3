package com.jayeondeule.smartfarm.dto.sensor;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SensorDataDTO {
    private LocalDateTime recdDttm; // 측정일자

    private double indrTprtValu; // 실내 온도
    private double indrHmdtValu; // 실내 습도
    private double oudrTprtValu; // 외부 온도
    private double oudrHmdtValu; // 외부 습도
    private double co2Valu; // 실내 co2
    private double watrTprtValu; // 수온
    private double lightLvelValu; // 조명 작동 상태
    private double watrLvelValu; // 관수 작동 상태
}
