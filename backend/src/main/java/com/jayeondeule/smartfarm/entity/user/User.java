package com.jayeondeule.smartfarm.entity.user;

import com.jayeondeule.smartfarm.converter.AuthLvelConverter;
import com.jayeondeule.smartfarm.enums.user.AuthLvel;
import com.jayeondeule.smartfarm.entity.farm.Farm;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "USER_M_INFO")
public class User {
    //USER_M_INFO 테이블에 대응하는 엔티티
    @Id
    private String userId; // 아이디

    @Setter
    @Column
    private long farmId;

    @Column(nullable = false)
    private String passwd; // 비밀번호 (암호화 필요)

    @Column(nullable = false)
    private String userName; // 이름

    @Column
    private String pstn; // 직위

    @Column(nullable = false)
    @Convert(converter = AuthLvelConverter.class)
    private AuthLvel authLvel = AuthLvel.MONITOR; // 권한 (ADMIN, FARM_ADMIN, HOUS_MANAGER, MONITOR)

    @Column
    private String hpNo; // 전화번호

    @Column(nullable = false, columnDefinition = "TIMESTAMP(6) WITHOUT TIME ZONE")
    private LocalDateTime rgstDttm = LocalDateTime.now(); // 가입일자

    //비밀번호 변경 용도
    public void changePassword(String newPassword) {
        this.passwd = newPassword;
    }

    //사용자 정보 수정용
    public void changeUserName(String newUserName) {
        this.userName = newUserName;
    }
    public void changeUserHpNo(String newHpNo) {
        this.hpNo = newHpNo;
    }
    public void changeUserPstn(String newPstn) {
        this.pstn = newPstn;
    }

    //사용자 권한 수정용
    public void changeUserAuthLvel(AuthLvel newAuthLvel) {
        this.authLvel = newAuthLvel;
    }
}
