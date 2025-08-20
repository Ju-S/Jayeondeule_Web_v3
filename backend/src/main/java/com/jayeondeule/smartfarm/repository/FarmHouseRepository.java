package com.jayeondeule.smartfarm.repository;

import com.jayeondeule.smartfarm.entity.house.FarmHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmHouseRepository extends JpaRepository<FarmHouse, Long> {
    //재배사 관련 데이터 CRUD 인터페이스
}
