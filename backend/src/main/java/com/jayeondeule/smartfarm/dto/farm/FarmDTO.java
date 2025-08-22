package com.jayeondeule.smartfarm.dto.farm;

import com.jayeondeule.smartfarm.enums.farm.Product;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

// 농장 조회 DTO
@Getter
@Setter
public class FarmDTO {
    //농장 관련 데이터 전송 객체
    private long farmId;

    private String farmName; // 농장 이름
    private String farmDomi; // 농장 도메인

    private LocalDate openDate; // 개업일자
    private LocalDate closeDate; // 폐업일자 (선택적)

    private String telNo; // 대표번호
    private String hpNo; // 전화번호
    private String faxNo; // 팩스번호
    private String mail; // 대표메일
    private String ipAddr; // 농장 IP 정보
    private String port; // 포트 정보
    private String addr; // 주소
    private Product mainPrdt; // 주요 작물
    private String rmks; // 농장 설명

    private LocalDateTime rgstDttm; // 등록일자
}
