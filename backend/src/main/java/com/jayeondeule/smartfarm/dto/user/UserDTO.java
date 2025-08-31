package com.jayeondeule.smartfarm.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jayeondeule.smartfarm.enums.user.AuthLvel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {
    //사용자 정보 전송 객체
    private String userId; // 아이디
    private long farmId; // 접근 권한 소유 농장
    private String userName; // 이름
    private String pstn; // 직위
    private AuthLvel authLvel; // 권한 (ADMIN, USER 등)
    private String hpNo; // 전화번호
    private LocalDateTime rgstDttm; // 가입 일자
}
