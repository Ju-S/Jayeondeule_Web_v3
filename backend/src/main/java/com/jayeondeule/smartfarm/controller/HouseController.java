package com.jayeondeule.smartfarm.controller;

import com.jayeondeule.smartfarm.dto.house.*;
import com.jayeondeule.smartfarm.dto.house.FarmHousePatchDTO;
import com.jayeondeule.smartfarm.dto.user.UserDTO;
import com.jayeondeule.smartfarm.service.HouseService;
import lombok.RequiredArgsConstructor;
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

    //재배사 등록
    @PostMapping
    public void insertFarmHouse(@RequestBody FarmHouseInsertDTO insertInfo,
                                @AuthenticationPrincipal UserDTO userInfo) {

    }

    //농장의 재배사 조회
    @GetMapping
    public ResponseEntity<List<FarmHouseDTO>> getFarmHouse(@PathVariable Long farmId,
                                                           @AuthenticationPrincipal UserDTO userInfo) {
        return null;
    }

    //재배사 정보 수정
    @PatchMapping("/{houseId}")
    public void patchFarmHouse(@RequestBody FarmHousePatchDTO modifiedInfo,
                               @PathVariable Long farmId,
                               @PathVariable Long houseId,
                               @AuthenticationPrincipal UserDTO userInfo) {

    }

    //재배사 삭제
    @DeleteMapping("/{houseId}")
    public void deleteFarmHouse(@PathVariable Long farmId,
                                @PathVariable Long houseId,
                                @AuthenticationPrincipal UserDTO userInfo) {

    }
}
