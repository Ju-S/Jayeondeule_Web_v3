package com.jayeondeule.smartfarm.repository;

import com.jayeondeule.smartfarm.entity.setting.SensorSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorSettingRepository extends JpaRepository<SensorSetting, Long> {
}
