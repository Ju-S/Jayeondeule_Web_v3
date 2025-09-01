package com.jayeondeule.smartfarm.controller;

import com.jayeondeule.smartfarm.dto.setting.LightIrrigationSettingPatchDTO;
import com.jayeondeule.smartfarm.dto.setting.SettingDTO;
import com.jayeondeule.smartfarm.dto.setting.SettingInsertDTO;
import com.jayeondeule.smartfarm.dto.user.UserClaimDTO;
import com.jayeondeule.smartfarm.enums.user.AuthLvel;
import com.jayeondeule.smartfarm.service.SettingService;
import com.jayeondeule.smartfarm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

//센서 최대, 최소값 설정 및 관수, 조명 시간 설정관련 API
@RestController
@RequestMapping("/api/farms/{farmId}/houses/{houseId}/settings")
@RequiredArgsConstructor
public class SettingController {

    private final SettingService settingService;
    private final UserService userService;

    //센서 최대, 최소값 설정
    //관수, 조명 시간 설정
    @PostMapping
    public void insertSettings(@RequestBody SettingInsertDTO settingInfo,
                                                  @PathVariable Long farmId,
                                                  @PathVariable Long houseId,
                                                  @AuthenticationPrincipal UserClaimDTO userInfo) {
        if(userInfo != null) {
            if(userInfo.getAuthLvel().equals(AuthLvel.ADMIN) ||
                    userService.getUserOwnedFarmId(userInfo.getUserId()) == farmId) {
                settingService.insertSettings(farmId, houseId, settingInfo);
            }
        }
    }

    //재배사 설정 정보 조회
    @GetMapping
    public ResponseEntity<SettingDTO> getSettings(@PathVariable Long farmId,
                                                  @PathVariable Long houseId,
                                                  @AuthenticationPrincipal UserClaimDTO userInfo) {
        //houseId의 farmId가 userInfo의 farmId에 있는지 확인
        if(userInfo != null) {
            if(userInfo.getAuthLvel().equals(AuthLvel.ADMIN) ||
                    userService.getUserOwnedFarmId(userInfo.getUserId()) == farmId) {
                return ResponseEntity.ok(settingService.getSettings(farmId, houseId));
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    // 관수, 조명 설정 변경
    @PatchMapping("/{setnDttm}")
    public void patchLightIrrigationSetting(@RequestBody LightIrrigationSettingPatchDTO modifiedInfo,
                                                               @PathVariable Long farmId,
                                                               @PathVariable Long houseId,
                                                               @PathVariable LocalDateTime setnDttm,
                                                               @AuthenticationPrincipal UserClaimDTO userInfo) {
        //lightIrrigationSetting의 houseId의 farmId가 userInfo의 farmId에 있는지 확인
        if(userInfo != null) {
            if(userInfo.getAuthLvel().equals(AuthLvel.ADMIN) ||
                    userService.getUserOwnedFarmId(userInfo.getUserId()) == farmId) {
                settingService.patchSetting(farmId, houseId, setnDttm, modifiedInfo);
            }
        }
    }

    // 관수, 조명 설정 삭제
    @DeleteMapping("/{setnDttm}")
    public void deleteLightIrrigationSetting(@PathVariable Long farmId,
                                             @PathVariable Long houseId,
                                             @PathVariable LocalDateTime setnDttm,
                                             @AuthenticationPrincipal UserClaimDTO userInfo) {
        if(userInfo != null) {
            if(userInfo.getAuthLvel().equals(AuthLvel.ADMIN) ||
                    userService.getUserOwnedFarmId(userInfo.getUserId()) == farmId) {
                settingService.deleteSetting(farmId, houseId, setnDttm);
            }
        }
    }
}
