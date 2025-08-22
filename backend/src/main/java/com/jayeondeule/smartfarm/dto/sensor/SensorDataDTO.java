package com.jayeondeule.smartfarm.dto.sensor;

import com.jayeondeule.smartfarm.entity.house.FarmHouse;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

public class SensorDataDTO {
    private LocalDateTime recdDttm; // 측정일자

    private double indrTprtValu; // 실내 온도
    private double indrHmdtValu; // 실내 습도
    private double oudrTprtValu; // 외부 온도
    private double oudrHmdtValu; // 외부 습도
    private double co2Valu; // 실내 co2
    private double watrTprtValu; // 수온
    private boolean lightLvelValu; // 조명 작동 상태
    private boolean watrLvelValu; // 관수 작동 상태
}
