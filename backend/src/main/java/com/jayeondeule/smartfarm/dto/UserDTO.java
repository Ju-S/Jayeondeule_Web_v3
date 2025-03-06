package com.jayeondeule.smartfarm.dto;

import jakarta.persistence.Column;

public class UserDTO {
    //사용자 정보 전송 객체

    private String user_id; // 아이디
    private String passwd; // 비밀번호 (암호화 필요)
    private String user_name; // 이름
    private String auth_lvel; // 권한 (ADMIN, USER 등)
    private String hp_no; // 전화번호
    private String pstn; // 직위
}
