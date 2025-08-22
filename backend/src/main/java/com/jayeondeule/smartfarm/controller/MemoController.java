package com.jayeondeule.smartfarm.controller;

import com.jayeondeule.smartfarm.dto.memo.FarmHouseCropsDTO;
import com.jayeondeule.smartfarm.dto.memo.FarmHouseCropsInsertDTO;
import com.jayeondeule.smartfarm.dto.memo.FarmHouseCropsPatchDTO;
import com.jayeondeule.smartfarm.dto.user.UserDTO;
import com.jayeondeule.smartfarm.entity.memo.FarmHouseCropsId;
import com.jayeondeule.smartfarm.service.MemoService;
import lombok.RequiredArgsConstructor;
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

    //메모 등록
    @PostMapping
    public void insertMemo(@RequestBody FarmHouseCropsInsertDTO insertInfo,
                           @PathVariable Long farmId,
                           @PathVariable Long houseId,
                           @AuthenticationPrincipal UserDTO userInfo) {

    }

    //메모 조회
    @GetMapping
    public ResponseEntity<List<FarmHouseCropsDTO>> getMemo(@PathVariable Long farmId,
                                                           @PathVariable Long houseId,
                                                           @AuthenticationPrincipal UserDTO userInfo) {
        return null;
    }

    //메모 수정
    @PatchMapping("/{recdDttm}")
    public void patchMemo(@RequestBody FarmHouseCropsPatchDTO modifiedInfo,
                          @PathVariable Long farmId,
                          @PathVariable Long houseId,
                          @PathVariable LocalDateTime recdDttm,
                          @AuthenticationPrincipal UserDTO userInfo) {
        FarmHouseCropsId id = new FarmHouseCropsId();
    }

    //메모 삭제
    @DeleteMapping("/{recdDttm}")
    public void deleteMemo(@PathVariable Long farmId,
                           @PathVariable Long houseId,
                           @PathVariable LocalDateTime recdDttm,
                           @AuthenticationPrincipal UserDTO userInfo) {

    }
}
