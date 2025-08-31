package com.jayeondeule.smartfarm.repository;

import com.jayeondeule.smartfarm.entity.house.FarmHouse;
import com.jayeondeule.smartfarm.entity.house.FarmHouseId;
import com.jayeondeule.smartfarm.entity.memo.FarmHouseCrops;
import com.jayeondeule.smartfarm.entity.memo.FarmHouseCropsId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface FarmHouseCropsRepository extends JpaRepository<FarmHouseCrops, FarmHouseCropsId> {
    Page<FarmHouseCrops> findAllByFarmIdAndHousId(long farmId, long housId, Pageable pageable);
}
