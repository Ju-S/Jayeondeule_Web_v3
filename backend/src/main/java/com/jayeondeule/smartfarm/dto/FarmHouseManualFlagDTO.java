package com.jayeondeule.smartfarm.dto;

import lombok.Getter;
import lombok.Setter;

//재배사 릴레이 수동 조작 update DTO
@Getter
@Setter
public class FarmHouseManualFlagDTO {
    private Long hous_id; //재배사 번호
    private Boolean mnul_ctrl_flag; //릴레이 수동 조작 여부
}
