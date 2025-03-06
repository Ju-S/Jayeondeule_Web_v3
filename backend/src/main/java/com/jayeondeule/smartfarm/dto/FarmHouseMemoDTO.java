package com.jayeondeule.smartfarm.dto;

import lombok.Getter;
import lombok.Setter;

//재배사 메모 DTO
@Getter
@Setter
public class FarmHouseMemoDTO {
    private String memo; // 메모내용
    private String author; // 작성자
}
