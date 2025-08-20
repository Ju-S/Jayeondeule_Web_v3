package com.jayeondeule.smartfarm.repository;

import com.jayeondeule.smartfarm.entity.farm.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmRepository extends JpaRepository<Farm, Long> {
    //농장 관련 데이터 CRUD 인터페이스
}
