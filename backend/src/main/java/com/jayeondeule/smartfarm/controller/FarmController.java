package com.jayeondeule.smartfarm.controller;

import com.jayeondeule.smartfarm.dto.farm.FarmDTO;
import com.jayeondeule.smartfarm.dto.farm.FarmInsertDTO;
import com.jayeondeule.smartfarm.dto.user.UserDTO;
import com.jayeondeule.smartfarm.dto.user.UserPatchDTO;
import com.jayeondeule.smartfarm.service.FarmService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//농장 등록, 수정, 조회 관련 API
@RestController
@RequestMapping("/api/farm")
@RequiredArgsConstructor
public class FarmController {
    private final FarmService farmService;

    //농장 등록
    @PostMapping
    public ResponseEntity<Boolean> insertFarm(@RequestBody FarmInsertDTO insertInfo,
                                              @AuthenticationPrincipal UserDTO userInfo) {
        return null;
    }

    //농장 리스트
    @GetMapping
    public ResponseEntity<List<FarmDTO>> getFarmList(@AuthenticationPrincipal UserDTO userInfo) {
        //jwt에서 userInfo를 조회해서 권한확인 후 분기
        return null;
    }

    //농장 정보 수정
    @PatchMapping
    public ResponseEntity<Boolean> patchFarm(@RequestBody UserPatchDTO modifiedInfo,
                                             @RequestParam String farmId,
                                             @AuthenticationPrincipal UserDTO userInfo
                                             ) {
        return null;
    }

    //농장 삭제
    @DeleteMapping
    public ResponseEntity<Boolean> deleteFarm(@RequestParam String farmId,
                                              @AuthenticationPrincipal UserDTO userInfo) {
        return null;
    }
}
