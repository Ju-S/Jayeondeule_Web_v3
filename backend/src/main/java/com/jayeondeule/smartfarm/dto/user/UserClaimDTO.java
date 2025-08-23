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
public class UserClaimDTO {
    //JWT에 저장될 사용자 정보 객체
    private String userId; // 아이디
    private AuthLvel authLvel; // 권한 (ADMIN, USER 등)
}
