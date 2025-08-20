package com.jayeondeule.smartfarm.controller;

import com.jayeondeule.smartfarm.dto.memo.MemoDTO;
import com.jayeondeule.smartfarm.dto.memo.MemoInsertDTO;
import com.jayeondeule.smartfarm.dto.memo.MemoPatchDTO;
import com.jayeondeule.smartfarm.dto.user.UserDTO;
import com.jayeondeule.smartfarm.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/memo")
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    //메모 등록
    @PostMapping
    public ResponseEntity<Boolean> insertMemo(@RequestBody MemoInsertDTO insertInfo,
                                              @AuthenticationPrincipal UserDTO userInfo) {
        return null;
    }

    //메모 조회
    @GetMapping
    public ResponseEntity<List<MemoDTO>> getMemo(@RequestParam String houseId,
                                                    @AuthenticationPrincipal UserDTO userInfo) {
        return null;
    }

    //메모 수정
    @PatchMapping
    public ResponseEntity<Boolean> patchMemo(@RequestBody MemoPatchDTO modifiedInfo,
                                             @RequestParam String memoId,
                                             @AuthenticationPrincipal UserDTO userInfo) {
        return null;
    }

    //메모 삭제
    @DeleteMapping
    public ResponseEntity<Boolean> deleteMemo(@RequestParam String memoId,
                                             @AuthenticationPrincipal UserDTO userInfo) {
        return null;
    }
}
