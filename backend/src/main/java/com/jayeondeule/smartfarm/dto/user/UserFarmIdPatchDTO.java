package com.jayeondeule.smartfarm.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jayeondeule.smartfarm.enums.user.AuthLvel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserFarmIdPatchDTO {
    //사용자 수정 정보 전송 객체
//    private String userId; // 권한을 부여할 사용자 ID
    private Long farmId; // 농장 접근 권한 식별을 위한 farmId
}
