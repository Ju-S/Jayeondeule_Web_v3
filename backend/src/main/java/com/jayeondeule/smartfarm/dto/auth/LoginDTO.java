package com.jayeondeule.smartfarm.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
    @NotBlank
    private String userId; // 아이디

    @NotBlank
    private String passwd; // 비밀번호 (암호화 필요)
}
