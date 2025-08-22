package com.jayeondeule.smartfarm.dto.farm;

import com.jayeondeule.smartfarm.enums.farm.Product;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

// 농장 등록 DTO
@Getter
@Setter
public class FarmInsertDTO {
    private String farmName; // 농장 이름
    private String farmDomi; // 농장 도메인

    private LocalDate openDate; // 개업일자

    private String telNo; // 대표번호
    private String hpNo; // 전화번호
    private String faxNo; // 팩스번호
    private String mail; // 대표메일
    private String ipAddr; // 농장 IP 정보
    private String port; // 포트 정보
    private String addr; // 주소
    private Product mainPrdt; // 주요 작물
    private String rmks; // 농장 설명
}
