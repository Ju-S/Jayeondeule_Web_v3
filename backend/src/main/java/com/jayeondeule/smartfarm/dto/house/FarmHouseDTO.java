package com.jayeondeule.smartfarm.dto.house;

import com.jayeondeule.smartfarm.entity.farm.Farm;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

//재배사 관련 데이터 전송 객체
@Getter
@Setter
public class FarmHouseDTO {
    private long housId;

    private String housName; // 재배사 이름

    private LocalDateTime lastGetDttm; // 마지막 작물 수확 일자

    private String snsrRfrsItvl;  // 센서 측정 시간 간격
    private boolean rfrsFlag; // 수동 센서 측정
    private String cropKind; // 작물 종류
    private boolean mnulCtrlFlag; // 릴레이 수동 조작 여부

    private LocalDateTime rgstDttm; // 등록일자
}
