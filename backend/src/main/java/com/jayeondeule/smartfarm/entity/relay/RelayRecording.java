package com.jayeondeule.smartfarm.entity.relay;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@IdClass(RelayRecording.class)
@Table(name = "RELAY_L_RECORDING")
public class RelayRecording {
    //RELAY_L_RECORDING 테이블 엔티티 (릴레이 동작상태, 저장일자 등)
    @Id
    private long farmId;

    @Id
    private long housId; // 릴레이가 속한 재배사

    @Id
    @Column(columnDefinition = "TIMESTAMP(6) WITHOUT TIME ZONE")
    private LocalDateTime recd_dttm = LocalDateTime.now();; // 측정일자

    @Column(nullable = false, name = "relay_1st_flag")
    private boolean relay1stFlag = false; // 릴레이1 작동 여부

    @Column(nullable = false, name = "relay_2st_flag")
    private boolean relay2stFlag = false; // 릴레이2 작동 여부

    @Column(nullable = false, name = "relay_3st_flag")
    private boolean relay3stFlag = false; // 릴레이3 작동 여부

    @Column(nullable = false, name = "relay_4st_flag")
    private boolean relay4stFlag = false; // 릴레이4 작동 여부

    @Column(nullable = false, name = "relay_5st_flag")
    private boolean relay5stFlag = false; // 릴레이5 작동 여부

    @Column(nullable = false, name = "relay_6st_flag")
    private boolean relay6stFlag = false; // 릴레이6 작동 여부

    @Column(nullable = false, name = "relay_7st_flag")
    private boolean relay7stFlag = false; // 릴레이7 작동 여부

    @Column(nullable = false, name = "relay_8st_flag")
    private boolean relay8stFlag = false; // 릴레이8 작동 여부

    @Column(nullable = false, name = "relay_9st_flag")
    private boolean relay9stFlag = false; // 릴레이9 작동 여부

    @Column(nullable = false, name = "relay_10st_flag")
    private boolean relay10stFlag = false; // 릴레이10 작동 여부

    @Column(nullable = false, name = "relay_11st_flag")
    private boolean relay11stFlag = false; // 릴레이11 작동 여부

    @Column(nullable = false, name = "relay_12st_flag")
    private boolean relay12stFlag = false; // 릴레이12 작동 여부

    @Column(nullable = false, name = "relay_13st_flag")
    private boolean relay13stFlag = false; // 릴레이13 작동 여부

    @Column(nullable = false, name = "relay_14st_flag")
    private boolean relay14stFlag = false; // 릴레이14 작동 여부

    @Column(nullable = false, name = "relay_15st_flag")
    private boolean relay15stFlag = false; // 릴레이15 작동 여부

    @Column(nullable = false, name = "relay_16st_flag")
    private boolean relay16stFlag = false; // 릴레이16 작동 여부
}
