package com.jayeondeule.smartfarm.service;

import com.jayeondeule.smartfarm.dto.DelLightIrrigationSettingDTO;
import com.jayeondeule.smartfarm.dto.UpdateLightIrrigationSettingDTO;
import com.jayeondeule.smartfarm.dto.UpdateSensorSettingDTO;

public class SettingService {
    //센서 최대, 최소값 설정
    public UpdateSensorSettingDTO updateSensorSetting(UpdateSensorSettingDTO sensorSettingInfo) {

        //TODO: sensorSettingInfo에 따라 센서 설정값 update 및 return

        return UpdateSensorSettingDTO;
    }

    //관수, 조명 시간 설정
    public UpdateLightIrrigationSettingDTO updateLightIrrigationSetting(UpdateLightIrrigationSettingDTO lightIrrigationSettingInfo) {

        //TODO: lightIrrigationSettingInfo에 따라 관수, 조명 설정 create 및 return

        return UpdateLightIrrigationSettingDTO;
    }

    //관수, 조명 설정 삭제
    public DelLightIrrigationSettingDTO delLightIrrigationSetting(DelLightIrrigationSettingDTO delLightIrrigationSettingInfo) {

        //TODO: delLightIrrigationSettingInfo에 따라 관수, 조명 설정 삭제 및 return

        return DelLightIrrigationSettingDTO;
    }
}
