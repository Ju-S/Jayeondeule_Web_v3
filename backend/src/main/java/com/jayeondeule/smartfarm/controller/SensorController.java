package com.jayeondeule.smartfarm.controller;

import com.jayeondeule.smartfarm.dto.house.FarmHouseDTO;
import com.jayeondeule.smartfarm.dto.sensor.SensorDataDTO;
import com.jayeondeule.smartfarm.dto.user.UserDTO;
import com.jayeondeule.smartfarm.service.SensorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//센서 데이터 수집 및 모니터링 API
@RestController
@RequestMapping("/api/sensor")
@RequiredArgsConstructor
public class SensorController {

    private final SensorService sensorService;

    //최신 센서 측정 값 조회
    @GetMapping
    public ResponseEntity<SensorDataDTO> getSensorData(@RequestParam String houseId,
                                                       @AuthenticationPrincipal UserDTO userInfo) {
        return null;
    }
}
