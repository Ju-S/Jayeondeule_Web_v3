package com.jayeondeule.smartfarm.controller;

import com.jayeondeule.smartfarm.dto.sensor.SensorDataDTO;
import com.jayeondeule.smartfarm.dto.user.UserClaimDTO;
import com.jayeondeule.smartfarm.enums.user.AuthLvel;
import com.jayeondeule.smartfarm.service.SensorService;
import com.jayeondeule.smartfarm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

//센서 데이터 수집 및 모니터링 API
@RestController
@RequestMapping("/api/farms/{farmId}/houses/{houseId}/sensors")
@RequiredArgsConstructor
public class SensorController {

    private final SensorService sensorService;
    private final UserService userService;

    //최신 센서 측정 값 조회
    @GetMapping
    public ResponseEntity<List<SensorDataDTO>> getSensorData(@PathVariable Long farmId,
                                                             @PathVariable Long houseId,
                                                             @RequestParam LocalDateTime start,
                                                             @RequestParam LocalDateTime end,
                                                             @AuthenticationPrincipal UserClaimDTO userInfo) {
        if (userInfo != null) {
            if (userInfo.getAuthLvel().equals(AuthLvel.ADMIN) ||
                    userService.getUserOwnedFarmId(userInfo.getUserId()) == farmId) {
                return ResponseEntity.ok(sensorService.getSensorHistory(farmId, houseId, start, end));
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
