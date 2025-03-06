package com.jayeondeule.smartfarm.controller;

import com.jayeondeule.smartfarm.dto.*;
import com.jayeondeule.smartfarm.service.SettingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//센서 최대, 최소값 설정 및 관수, 조명 시간 설정관련 API
@RestController
public class SettingController {

    private final SettingService settingService;

    public SettingController(SettingService settingService) {
        this.settingService = settingService;
    }

    //센서 최대, 최소값 설정
    @PostMapping("/updateSensorSetting")
    public ResponseEntity<UpdateSensorSettingDTO> updateSensorSetting(@RequestBody UpdateSensorSettingDTO sensorSettingInfo) {
        return ResponseEntity.ok(settingService.updateSensorSetting(sensorSettingInfo));
    }

    //관수, 조명 시간 설정
    @PostMapping("/updateLightIrrigationSetting")
    public ResponseEntity<UpdateLightIrrigationSettingDTO> updateLightIrrigationSetting(@RequestBody UpdateLightIrrigationSettingDTO lightIrrigationSettingInfo) {
        return ResponseEntity.ok(settingService.updateLightIrrigationSetting(lightIrrigationSettingInfo));
    }

    //관수, 조명 설정 삭제
    @PostMapping("/delLightIrrigationSetting")
    public ResponseEntity<DelLightIrrigationSettingDTO> delLightIrrigationSetting(@RequestBody DelLightIrrigationSettingDTO delLightIrrigationSettingInfo) {
        return ResponseEntity.ok(settingService.delLightIrrigationSetting(delLightIrrigationSettingInfo));
    }
}
