package com.jayeondeule.smartfarm.controller;

import com.jayeondeule.smartfarm.dto.house.*;
import com.jayeondeule.smartfarm.dto.house.FarmHousePatchDTO;
import com.jayeondeule.smartfarm.dto.user.UserClaimDTO;
import com.jayeondeule.smartfarm.enums.user.AuthLvel;
import com.jayeondeule.smartfarm.service.FarmService;
import com.jayeondeule.smartfarm.service.HouseService;
import com.jayeondeule.smartfarm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//재배사 등록, 모니터링, 설정 API
@RestController
@RequestMapping("/api/farms/{farmId}/houses")
@RequiredArgsConstructor
public class HouseController {

    private final HouseService houseService;
    private final UserService userService;

    //재배사 등록
    @PostMapping
    public void insertFarmHouse(@RequestBody FarmHouseInsertDTO insertInfo,
                                @AuthenticationPrincipal UserClaimDTO userInfo) {

    }

    //농장의 재배사 조회
    @GetMapping
    public ResponseEntity<List<FarmHouseDTO>> getFarmHouse(@PathVariable Long farmId,
                                                           @AuthenticationPrincipal UserClaimDTO userInfo) {
        if (userInfo != null) {
            if (userInfo.getAuthLvel().equals(AuthLvel.ADMIN) ||
                    userService.getUserOwnedFarmId(userInfo.getUserId()) == farmId) {
                return ResponseEntity.ok(houseService.getHouseList(farmId));
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    //재배사 정보 수정
    @PatchMapping("/{houseId}")
    public void patchFarmHouse(@RequestBody FarmHousePatchDTO modifiedInfo,
                               @PathVariable Long farmId,
                               @PathVariable Long houseId,
                               @AuthenticationPrincipal UserClaimDTO userInfo) {

    }

    //재배사 삭제
    @DeleteMapping("/{houseId}")
    public void deleteFarmHouse(@PathVariable Long farmId,
                                @PathVariable Long houseId,
                                @AuthenticationPrincipal UserClaimDTO userInfo) {

    }
}
