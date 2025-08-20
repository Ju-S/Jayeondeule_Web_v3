package com.jayeondeule.smartfarm.dto.house;

import lombok.Getter;
import lombok.Setter;

//재배사 릴레이 수동 조작 update DTO
@Getter
@Setter
public class FarmHousePatchDTO {
    private Long hous_id; //재배사 번호
    private Boolean mnul_ctrl_flag; //릴레이 수동 조작 여부
}
