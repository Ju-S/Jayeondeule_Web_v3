package com.jayeondeule.smartfarm.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "FARM_M_INFO")
public class Farm {
    //FARM_M_INFO 테이블에 대응하는 엔티티

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long farm_id;

    @Column(nullable = false, unique = true)
    private String farm_name; // 농장 이름

    @Column(nullable = false, unique = true)
    private String farm_domi; // 농장 도메인

    @Column(nullable = false)
    private LocalDateTime open_date; // 개업일자

    @Column
    private LocalDateTime close_date; // 폐업일자 (선택적)

    @Column
    private String tel_no; // 대표번호

    @Column
    private String hp_no; // 전화번호

    @Column
    private String fax_no; // 팩스번호

    @Column
    private String mail; // 대표메일

    @Column(nullable = false)
    private String ip_addr; // 농장 IP 정보

    @Column(nullable = false)
    private String port; // 포트 정보

    private String regn; // 지역

    private String addr; // 주소

    private String main_prdt; // 주요 작물

    @Column(columnDefinition = "TEXT")
    private String rmks; // 농장 설명

    @Column(nullable = false)
    private LocalDateTime rgst_dttm = LocalDateTime.now(); // 등록일자
}
