package com.jayeondeule.smartfarm.repository;

import com.jayeondeule.smartfarm.entity.setting.LightIrrigationSetting;
import com.jayeondeule.smartfarm.entity.setting.LightIrrigationSettingId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface LightIrrigationSettingRepository extends JpaRepository<LightIrrigationSetting, LightIrrigationSettingId> {
    List<LightIrrigationSetting> findAllByFarmIdAndHousIdOrderByStrtTime(long farmId, long houseId);
}
