package com.jayeondeule.smartfarm.dto;

import lombok.Getter;
import lombok.Setter;

//재배사 관련 데이터 전송 객체
@Getter
@Setter
public class FarmHouseDTO {
    private Long hous_id; //재배사 번호
    private String hous_name; //재배사 이름
    private String crop_kind; //작물 종류
}
