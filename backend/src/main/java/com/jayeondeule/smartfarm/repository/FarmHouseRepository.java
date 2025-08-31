package com.jayeondeule.smartfarm.repository;

import com.jayeondeule.smartfarm.entity.house.FarmHouse;
import com.jayeondeule.smartfarm.entity.house.FarmHouseId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FarmHouseRepository extends JpaRepository<FarmHouse, FarmHouseId> {
    List<FarmHouse> findAllByFarmId(long farmId);

    List<FarmHouse> findAllByFarmIdOrderByHousNameAsc(long farmId);
    //재배사 관련 데이터 CRUD 인터페이스
}
