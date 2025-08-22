package com.jayeondeule.smartfarm.dto.farm;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

// 농장 등록 DTO
@Getter
@Setter
public class FarmPatchDTO {
    //농장 관련 데이터 전송 객체
    private String farmName; // 농장 이름
    private String farmDomi; // 농장 도메인

    private LocalDateTime openDate; // 개업일자
    private LocalDateTime closeDate; // 폐업일자 (선택적)

    private String telNo; // 대표번호
    private String hpNo; // 전화번호
    private String faxNo; // 팩스번호
    private String email; // 대표메일
    private String ipAddr; // 농장 IP 정보
    private String port; // 포트 정보
    private String addr; // 주소
    private String mainPrdt; // 주요 작물
    private String rmks; // 농장 설명
}
