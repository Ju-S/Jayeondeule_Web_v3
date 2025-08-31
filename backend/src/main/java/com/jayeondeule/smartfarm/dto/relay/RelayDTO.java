package com.jayeondeule.smartfarm.dto.relay;

import com.jayeondeule.smartfarm.entity.house.FarmHouse;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RelayDTO {
    private LocalDateTime recdDttm; // 측정일자

    private boolean relay1stFlag = false; // 릴레이1 작동 여부
    private boolean relay2stFlag = false; // 릴레이2 작동 여부
    private boolean relay3stFlag = false; // 릴레이3 작동 여부
    private boolean relay4stFlag = false; // 릴레이4 작동 여부
    private boolean relay5stFlag = false; // 릴레이5 작동 여부
    private boolean relay6stFlag = false; // 릴레이6 작동 여부
    private boolean relay7stFlag = false; // 릴레이7 작동 여부
    private boolean relay8stFlag = false; // 릴레이8 작동 여부
    private boolean relay9stFlag = false; // 릴레이9 작동 여부
    private boolean relay10stFlag = false; // 릴레이10 작동 여부
    private boolean relay11stFlag = false; // 릴레이11 작동 여부
    private boolean relay12stFlag = false; // 릴레이12 작동 여부
    private boolean relay13stFlag = false; // 릴레이13 작동 여부
    private boolean relay14stFlag = false; // 릴레이14 작동 여부
    private boolean relay15stFlag = false; // 릴레이15 작동 여부
    private boolean relay16stFlag = false; // 릴레이16 작동 여부
}
