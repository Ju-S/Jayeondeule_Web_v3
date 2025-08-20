package com.jayeondeule.smartfarm.dto.house;

import lombok.Getter;
import lombok.Setter;

//재배사 등록 DTO
@Getter
@Setter
public class FarmHouseInsertDTO {
    private String hous_name; //재배사 이름
    private String crop_kind; //작물 종류
}
