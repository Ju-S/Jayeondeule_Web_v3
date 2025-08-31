package com.jayeondeule.smartfarm.dto.house;

import com.jayeondeule.smartfarm.entity.farm.Farm;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

//재배사 등록 DTO
@Getter
@Setter
public class FarmHouseInsertDTO {
    private long farmId; // 농장과 관계 설정
    // hous_id는 farmId로 찾은 house의 갯수+1로 설정.
    private long housId;
    private String housName; // 재배사 이름
    private String cropKind; // 작물 종류
}
