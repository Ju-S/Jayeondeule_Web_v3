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
@RequestMapping("/api/house")
@RequiredArgsConstructor
public class HouseController {

    private final HouseService houseService;

    //재배사 등록
    @PostMapping
    public ResponseEntity<Boolean> insertFarmHouse(@RequestBody FarmHouseInsertDTO insertInfo,
                                                   @AuthenticationPrincipal UserDTO userInfo) {
        return null;
    }

    //농장의 재배사 조회
    @GetMapping
    public ResponseEntity<List<FarmHouseDTO>> getFarmHouse(@RequestParam String farmId,
                                                           @AuthenticationPrincipal UserDTO userInfo) {
        return null;
    }

    //재배사 정보 수정
    @PatchMapping
    public ResponseEntity<Boolean> patchFarmHouse(@RequestBody FarmHousePatchDTO modifiedInfo,
                                                  @RequestParam String houseId,
                                                  @AuthenticationPrincipal UserDTO userInfo) {
        return null;
    }

    //재배사 삭제
    @DeleteMapping
    public ResponseEntity<Boolean> deleteFarmHouse(@RequestParam String houseId,
                                                   @AuthenticationPrincipal UserDTO userInfo) {
        return null;
    }
}
