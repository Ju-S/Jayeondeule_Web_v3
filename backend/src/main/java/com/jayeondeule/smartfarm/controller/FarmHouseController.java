package com.jayeondeule.smartfarm.controller;

import com.jayeondeule.smartfarm.dto.*;
import com.jayeondeule.smartfarm.service.FarmHouseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//재배사 등록, 모니터링, 설정 API
@RestController
public class FarmHouseController {

    private final FarmHouseService farmHouseService;

    public FarmHouseController(FarmHouseService farmHouseService) {
        this.farmHouseService = farmHouseService;
    }

    //재배사 등록
    @PostMapping("/farmHouseRegister")
    public ResponseEntity<FarmHouseDTO> farmHouseRegister(@Valid @RequestBody FarmHouseRegisterDTO farmHouseRegisterInfo) {
        return ResponseEntity.ok(farmHouseService.farmHouseRegister(farmHouseRegisterInfo));
    }

    //재배사 메모 저장
    @PostMapping("/saveFarmHouseMemo")
    public ResponseEntity<FarmHouseMemoDTO> saveFarmHouseMemo(@Valid @RequestBody FarmHouseMemoDTO memo) {
        return ResponseEntity.ok(farmHouseService.saveFarmHouseMemo(memo));
    }

    //재배사 수동 조작 flag update
    @PostMapping("/manualFlagUpdate")
    public ResponseEntity<FarmHouseManualFlagDTO> updateManualFlag(@Valid @RequestBody FarmHouseManualFlagDTO updateFlagInfo) {
        return ResponseEntity.ok(farmHouseService.updateManualFlag(updateFlagInfo));
    }

    //재배사 센서 측정 시간 간격 설정
    @PostMapping("/sensorIntervalUpdate")
    public ResponseEntity<SensorIntervalUpdateDTO> updateManualFlag(@Valid @RequestBody SensorIntervalUpdateDTO sensorInterval) {
        return ResponseEntity.ok(farmHouseService.updateSensorInterval(sensorInterval));
    }
}
