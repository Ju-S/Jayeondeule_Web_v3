package com.jayeondeule.smartfarm.controller;

import com.jayeondeule.smartfarm.dto.FarmHouseDTO;
import com.jayeondeule.smartfarm.dto.SensorDataDTO;
import com.jayeondeule.smartfarm.service.SensorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//센서 데이터 수집 및 모니터링 API
@RestController
public class SensorController {

    private final SensorService sensorService;

    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    //최신 센서 측정 값 조회
    @GetMapping("/getSensorInfo")
    public ResponseEntity<SensorDataDTO> getSensorInfo(@RequestParam FarmHouseDTO houseInfo) {
        return ResponseEntity.ok(sensorService.getSensorInfo(houseInfo));
    }

}
