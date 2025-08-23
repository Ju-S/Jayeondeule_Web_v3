package com.jayeondeule.smartfarm.dto.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserPasswordPatchDTO {
    private String oldPassword;
    private String newPassword;
}
