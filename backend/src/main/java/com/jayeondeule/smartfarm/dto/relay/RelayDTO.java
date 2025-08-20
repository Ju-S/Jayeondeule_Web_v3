package com.jayeondeule.smartfarm.dto.relay;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RelayDTO {
    //릴레이 동작 상태 조회 및 업데이트 객체
    private Long hous_id; // 설정이 속한 재배사

    private Boolean relay_1st_flag; // 릴레이1 작동 여부
    private Boolean relay_2st_flag; // 릴레이2 작동 여부
    private Boolean relay_3st_flag; // 릴레이3 작동 여부
    private Boolean relay_4st_flag; // 릴레이4 작동 여부
    private Boolean relay_5st_flag; // 릴레이5 작동 여부
    private Boolean relay_6st_flag; // 릴레이6 작동 여부
    private Boolean relay_7st_flag; // 릴레이7 작동 여부
    private Boolean relay_8st_flag; // 릴레이8 작동 여부
    private Boolean relay_9st_flag; // 릴레이9 작동 여부
    private Boolean relay_10st_flag; // 릴레이10 작동 여부
    private Boolean relay_11st_flag; // 릴레이11 작동 여부
    private Boolean relay_12st_flag; // 릴레이12 작동 여부
    private Boolean relay_13st_flag; // 릴레이13 작동 여부
    private Boolean relay_14st_flag; // 릴레이14 작동 여부
    private Boolean relay_15st_flag; // 릴레이15 작동 여부
    private Boolean relay_16st_flag; // 릴레이16 작동 여부
    private LocalDateTime recd_dttm; // 측정일자
}
