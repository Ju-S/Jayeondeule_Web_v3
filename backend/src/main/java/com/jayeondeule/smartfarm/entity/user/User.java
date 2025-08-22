package com.jayeondeule.smartfarm.entity.user;

import com.jayeondeule.smartfarm.enums.user.AuthLvel;
import com.jayeondeule.smartfarm.entity.farm.Farm;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "USER_M_INFO")
public class User {
    //USER_M_INFO 테이블에 대응하는 엔티티
    @Id
    private String userId; // 아이디

    @Column
    private long farmId;

    @Column(nullable = false)
    private String passwd; // 비밀번호 (암호화 필요)

    @Column(nullable = false)
    private String userName; // 이름

    @Column
    private String email; // 이메일

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AuthLvel authLvel = AuthLvel.MONITOR; // 권한 (ADMIN, FARM_ADMIN, HOUS_MANAGER, MONITOR)

    @Column
    private String hpNo; // 전화번호

    @Column(nullable = false, columnDefinition = "TIMESTAMP(6) WITHOUT TIME ZONE")
    private LocalDateTime rgstDttm = LocalDateTime.now(); // 가입일자
}
