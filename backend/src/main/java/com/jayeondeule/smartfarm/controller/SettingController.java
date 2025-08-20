package com.jayeondeule.smartfarm.controller;

import com.jayeondeule.smartfarm.dto.setting.SettingDTO;
import com.jayeondeule.smartfarm.dto.setting.SettingInsertDTO;
import com.jayeondeule.smartfarm.dto.user.UserDTO;
import com.jayeondeule.smartfarm.service.SettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

//센서 최대, 최소값 설정 및 관수, 조명 시간 설정관련 API
@RestController
@RequestMapping("/api/setting")
@RequiredArgsConstructor
public class SettingController {

    private final SettingService settingService;

    //센서 최대, 최소값 설정
    //관수, 조명 시간 설정
    @PostMapping
    public ResponseEntity<Boolean> insertSettings(@RequestBody SettingInsertDTO settingInfo,
                                                  @RequestParam String houseId,
                                                  @AuthenticationPrincipal UserDTO userInfo) {
        return null;
    }

    //재배사 설정 정보 조회
    @GetMapping
    public ResponseEntity<SettingDTO> getSettings(@RequestParam String houseId,
                                                  @AuthenticationPrincipal UserDTO userInfo) {
        //houseId의 farmId가 userInfo의 farmId에 있는지 확인
        return null;
    }

    @PatchMapping
    public ResponseEntity<Boolean> patchLightIrrigationSetting(@RequestParam String lightIrrigationSettingId,
                                                               @AuthenticationPrincipal UserDTO userInfo) {
        //lightIrrigationSetting의 houseId의 farmId가 userInfo의 farmId에 있는지 확인
        return null;
    }
}
