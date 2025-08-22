package com.jayeondeule.smartfarm.dto.memo;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

//재배사 메모 DTO
@Getter
@Setter
public class FarmHouseCropsDTO {
    private long farmId; // 작물이 속한 농장
    private long housId; // 작물이 속한 재배사
    private LocalDateTime recdDttm = LocalDateTime.now(); // 기록일자

    private int cropStat;
    private int cropQtty;

    private int cropGradeQtty1 = 0;
    private int cropGradeQtty2 = 0;
    private int cropGradeQtty3 = 0;
    private int cropGradeQtty4 = 0;
    private int cropGradeQtty5 = 0;

    private int cropGradeAmut1 = 0;
    private int cropGradeAmut2 = 0;
    private int cropGradeAmut3 = 0;
    private int cropGradeAmut4 = 0;
    private int cropGradeAmut5 = 0;

    private String athr; // 작성자
    private String rmks; // 메모

    private String orgn; // 업로드 이미지 파일명
    private String sstm; // 업로드 이미지 실제 경로

    private LocalDateTime cropStrtDate; // 작물 재배 시작 일자
    private LocalDateTime cropEndDate; // 작물 재배 종료 일자
}
