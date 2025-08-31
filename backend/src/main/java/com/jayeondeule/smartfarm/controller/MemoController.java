package com.jayeondeule.smartfarm.controller;

import com.jayeondeule.smartfarm.dto.memo.FarmHouseCropsDTO;
import com.jayeondeule.smartfarm.dto.memo.FarmHouseCropsInsertDTO;
import com.jayeondeule.smartfarm.dto.memo.FarmHouseCropsPatchDTO;
import com.jayeondeule.smartfarm.dto.user.UserClaimDTO;
import com.jayeondeule.smartfarm.dto.user.UserDTO;
import com.jayeondeule.smartfarm.entity.memo.FarmHouseCropsId;
import com.jayeondeule.smartfarm.enums.user.AuthLvel;
import com.jayeondeule.smartfarm.service.FarmService;
import com.jayeondeule.smartfarm.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/farms/{farmId}/houses/{houseId}/memos")
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;
    private final FarmService farmService;

    //메모 등록
    @PostMapping
    public void insertMemo(@RequestBody FarmHouseCropsInsertDTO insertInfo,
                           @PathVariable Long farmId,
                           @PathVariable Long houseId,
                           @AuthenticationPrincipal UserClaimDTO userInfo) {
        if (userInfo != null) {
            if (userInfo.getAuthLvel().equals(AuthLvel.ADMIN) ||
                    farmService.getFarmByUserId(userInfo.getUserId()).getFarmId() == farmId) {
                memoService.insertMemo(farmId, houseId, insertInfo, userInfo);
            }
        }
    }

    //메모 조회
    @GetMapping
    public ResponseEntity<Page<FarmHouseCropsDTO>> getMemo(@PathVariable Long farmId,
                                                           @PathVariable Long houseId,
                                                           @RequestParam(defaultValue = "0") int page,  // 0부터 시작
                                                           @RequestParam(defaultValue = "10") int size,  // 페이지 크기
                                                           @AuthenticationPrincipal UserClaimDTO userInfo) {
        if (userInfo != null) {
            if (userInfo.getAuthLvel().equals(AuthLvel.ADMIN) ||
                    farmService.getFarmByUserId(userInfo.getUserId()).getFarmId() == farmId) {
                Page<FarmHouseCropsDTO> memos = memoService.getMemos(farmId, houseId, page, size);
                return ResponseEntity.ok(memos);
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    //메모 수정
    @PatchMapping("/{recdDttm}")
    public void patchMemo(@RequestBody FarmHouseCropsPatchDTO modifiedInfo,
                          @PathVariable Long farmId,
                          @PathVariable Long houseId,
                          @PathVariable LocalDateTime recdDttm,
                          @AuthenticationPrincipal UserClaimDTO userInfo) {
        if (userInfo != null) {
            if (userInfo.getAuthLvel().equals(AuthLvel.ADMIN) ||
                    farmService.getFarmByUserId(userInfo.getUserId()).getFarmId() == farmId) {
                memoService.patchMemo(farmId, houseId, recdDttm, modifiedInfo.getMemo());
            }
        }
    }

    //메모 삭제
    @DeleteMapping("/{recdDttm}")
    public void deleteMemo(@PathVariable Long farmId,
                           @PathVariable Long houseId,
                           @PathVariable LocalDateTime recdDttm,
                           @AuthenticationPrincipal UserClaimDTO userInfo) {
        if (userInfo != null) {
            if (userInfo.getAuthLvel().equals(AuthLvel.ADMIN) ||
                    farmService.getFarmByUserId(userInfo.getUserId()).getFarmId() == farmId) {
                memoService.deleteMemo(farmId, houseId, recdDttm);
            }
        }
    }
}
