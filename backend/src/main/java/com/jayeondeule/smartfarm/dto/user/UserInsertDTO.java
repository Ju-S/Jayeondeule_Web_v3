package com.jayeondeule.smartfarm.dto.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInsertDTO {
    //사용자 정보 전송 객체

    private String userId; // 아이디
    private String passwd; // 비밀번호 (암호화 필요)
    private String userName; // 이름
    private String email; // 이메일
    private String hpNo; // 전화번호
}
