package com.jayeondeule.smartfarm.controller;

import com.jayeondeule.smartfarm.dto.sensor.SensorDataDTO;
import com.jayeondeule.smartfarm.dto.user.UserDTO;
import com.jayeondeule.smartfarm.service.SensorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//센서 데이터 수집 및 모니터링 API
@RestController
@RequestMapping("/api/farms/{farmId}/houses/{houseId}/sensors")
@RequiredArgsConstructor
public class SensorController {

    private final SensorService sensorService;

    //최신 센서 측정 값 조회
    @GetMapping
    public ResponseEntity<List<SensorDataDTO>> getSensorData(@PathVariable Long farmId,
                                                             @PathVariable Long houseId,
                                                             @RequestParam(required = false, defaultValue = "10") String limits,
                                                             @AuthenticationPrincipal UserDTO userInfo) {
        return null;
    }
}
