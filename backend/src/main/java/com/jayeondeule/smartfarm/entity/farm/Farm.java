package com.jayeondeule.smartfarm.entity.farm;

import com.jayeondeule.smartfarm.converter.ProductConverter;
import com.jayeondeule.smartfarm.enums.farm.Product;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "FARM_M_INFO")
public class Farm {
    //FARM_M_INFO 테이블에 대응하는 엔티티
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "farm_seq_gen"
    )
    @SequenceGenerator(
            name = "farm_seq_gen",
            sequenceName = "farm_id_seq",
            allocationSize = 1,
            initialValue = 1000
    )
    private long farmId;

    @Setter
    @Column(nullable = false)
    private String farmName; // 농장 이름

    @Setter
    @Column(nullable = false)
    private String farmDomi; // 농장 도메인

    @Setter
    @Column(nullable = false)
    private LocalDate openDate; // 개업일자

    @Setter
    @Column
    private LocalDate closeDate; // 폐업일자 (선택적)

    @Setter
    @Column
    private String telNo; // 대표번호

    @Setter
    @Column
    private String hpNo; // 전화번호

    @Setter
    @Column
    private String faxNo; // 팩스번호

    @Setter
    @Column
    private String mail; // 대표메일

    @Setter
    @Column(nullable = false)
    private String ipAddr; // 농장 IP 정보

    @Setter
    @Column(nullable = false)
    private String port; // 포트 정보

    @Column
    private int regn; // 지역 번호

    @Setter
    @Column
    private String addr; // 주소

    @Setter
    @Column
    @Convert(converter = ProductConverter.class)
    private Product mainPrdt; // 주요 작물

    @Setter
    @Column(columnDefinition = "TEXT")
    private String rmks; // 농장 설명

    @Column(nullable = false, columnDefinition = "TIMESTAMP(6) WITHOUT TIME ZONE")
    private LocalDateTime rgstDttm = LocalDateTime.now(); // 등록일자
}
