package com.jayeondeule.smartfarm.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "USER_M_INFO")
public class User {
    //USER_M_INFO 테이블에 대응하는 엔티티

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "farm_id")
    private Farm farm;

    @Column(nullable = false, unique = true, length = 50)
    private String user_id; // 아이디

    @Column(nullable = false)
    private String passwd; // 비밀번호 (암호화 필요)

    @Column(nullable = false)
    private String user_name; // 이름

    @Column(nullable = false)
    private String auth_lvel; // 권한 (ADMIN, USER 등)

    @Column
    private String hp_no; // 전화번호

    @Column
    private String pstn; // 직위

    @Column(nullable = false)
    private LocalDateTime rgst_dttm = LocalDateTime.now(); // 가입일자
}
