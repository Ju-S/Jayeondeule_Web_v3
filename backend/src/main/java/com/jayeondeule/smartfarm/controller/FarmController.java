package com.jayeondeule.smartfarm.controller;

import com.jayeondeule.smartfarm.dto.farm.FarmDTO;
import com.jayeondeule.smartfarm.dto.farm.FarmInsertDTO;
import com.jayeondeule.smartfarm.dto.farm.FarmPatchDTO;
import com.jayeondeule.smartfarm.dto.user.UserClaimDTO;
import com.jayeondeule.smartfarm.enums.user.AuthLvel;
import com.jayeondeule.smartfarm.service.FarmService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//농장 등록, 수정, 조회 관련 API
@RestController
@RequestMapping("/api/farms")
@RequiredArgsConstructor
public class FarmController {
    private final FarmService farmService;

    //농장 등록
    @PostMapping
    public void insertFarm(@RequestBody FarmInsertDTO insertInfo,
                           @AuthenticationPrincipal UserClaimDTO userInfo) {
        if (userInfo != null) {
            if (userInfo.getAuthLvel().equals(AuthLvel.ADMIN)) {
                farmService.insertFarm(insertInfo);
            }
        }
    }

    //농장 리스트
    @GetMapping
    public ResponseEntity<Page<FarmDTO>> getFarmList(
            @AuthenticationPrincipal UserClaimDTO userInfo,
            @RequestParam int page,
            @RequestParam int size) {
        //jwt에서 userInfo를 조회해서 권한확인 후 분기
        if (userInfo != null) {
            if (userInfo.getAuthLvel().equals(AuthLvel.ADMIN)) {
                return ResponseEntity.ok(farmService.getAllFarms(page, size));
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @GetMapping("/me")
    public ResponseEntity<FarmDTO> getMyFarm(@AuthenticationPrincipal UserClaimDTO userInfo) {
        if (userInfo != null) {
            return ResponseEntity.ok(farmService.getFarmByUserId(userInfo.getUserId()));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @GetMapping("/{farmId}")
    public ResponseEntity<FarmDTO> getFarm(@PathVariable Long farmId,
                                           @AuthenticationPrincipal UserClaimDTO userInfo) {
        if (userInfo != null) {
            if (userInfo.getAuthLvel().equals(AuthLvel.ADMIN) ||
                    farmService.getFarmByUserId(userInfo.getUserId()).getFarmId() == farmId) {
                return ResponseEntity.ok(farmService.getFarmByFarmId(farmId));
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    //농장 정보 수정
    @PatchMapping("/{farmId}")
    public void patchFarm(@RequestBody FarmPatchDTO modifiedInfo,
                          @PathVariable Long farmId,
                          @AuthenticationPrincipal UserClaimDTO userInfo
    ) {
        if (userInfo != null) {
            if (userInfo.getAuthLvel().equals(AuthLvel.ADMIN)) {
                farmService.patchFarmByFarmId(farmId, modifiedInfo);
            } else if (farmService.getFarmByUserId(userInfo.getUserId()).getFarmId() == farmId) {
//                farmService.patchFarmByFarmId(farmId, modifiedInfo);
            }
        }
    }

    //농장 삭제
    @DeleteMapping("/{farmId}")
    public void deleteFarm(@PathVariable Long farmId,
                           @AuthenticationPrincipal UserClaimDTO userInfo) {
        if (userInfo != null) {
            if (userInfo.getAuthLvel().equals(AuthLvel.ADMIN)) {
                farmService.deleteFarmByFarmId(farmId);
            } else if (farmService.getFarmByUserId(userInfo.getUserId()).getFarmId() == farmId) {
//                farmService.deleteFarmByFarmId(farmId);
            }
        }
    }
}
