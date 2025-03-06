package com.jayeondeule.smartfarm.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

// 농장 등록 DTO
@Getter
@Setter
public class FarmRegisterDTO {
    private String farm_name; // 농장 이름
    private String farm_domi; // 농장 도메인
    private LocalDateTime open_date; // 개업일자
    private String tel_no; // 대표번호
    private String hp_no; // 전화번호
    private String fax_no; // 팩스번호
    private String mail; // 대표메일
    private String ip_addr; // 농장 IP 정보
    private String port; // 포트 정보
    private String regn; // 지역
    private String addr; // 주소
    private String main_prdt; // 주요 작물
    private String rmks; // 농장 설명
}
