package com.jayeondeule.smartfarm.dto.memo;

import lombok.Getter;
import lombok.Setter;

//재배사 메모 DTO
@Getter
@Setter
public class FarmHouseCropsInsertDTO {
    private long farmId; // 메모가 속한 농장
    private long housId; // 메모가 속한 재배사

    private String memo; // 메모내용
    private String author; // 작성자
}
