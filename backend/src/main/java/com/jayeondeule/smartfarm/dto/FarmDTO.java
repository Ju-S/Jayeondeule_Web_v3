package com.jayeondeule.smartfarm.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

// 농장 조회 DTO
@Getter
@Setter
public class FarmDTO {
    //농장 관련 데이터 전송 객체
    private Long farm_id; // 농장 고유 번호
    private String farm_name; // 농장 이름
    private LocalDateTime rgst_dttm; // 농장 등록 일자
}
