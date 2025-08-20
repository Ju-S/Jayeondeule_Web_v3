package com.jayeondeule.smartfarm.entity.memo;

import com.jayeondeule.smartfarm.entity.house.FarmHouse;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "FARMHOUSE_L_MEMO")
public class FarmHouseMemoRecording {
    //FARMHOUSE_L_MEMO 각 재배사의 메모 테이블에 대응하는 엔티티
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "farmhous_memo_seq_gen"
    )
    @SequenceGenerator(
            name = "farmhous_memo_seq_gen",
            sequenceName = "farmhous_memo_seq",
            allocationSize = 1,
            initialValue = 0
    )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hous_id", nullable = false)
    private FarmHouse hous; // 메모가 속한 재배사

    @Column(nullable = false)
    private String memo; // 메모내용

    @Column(nullable = false)
    private String author; // 작성자

    @Column(nullable = false)
    private LocalDateTime recdDttm = LocalDateTime.now(); // 기록일자
}
