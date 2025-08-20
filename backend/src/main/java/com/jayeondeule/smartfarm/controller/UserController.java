package com.jayeondeule.smartfarm.controller;

import com.jayeondeule.smartfarm.dto.user.UserDTO;
import com.jayeondeule.smartfarm.dto.user.UserPatchDTO;
import com.jayeondeule.smartfarm.dto.user.UserInsertDTO;
import com.jayeondeule.smartfarm.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

//회원가입, 로그인 등 사용자 관련 API 엔드포인트
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    //회원가입
    @PostMapping
    public ResponseEntity<Boolean> signup(@RequestBody @Valid UserInsertDTO userInfo) {
        if (userService.signup(userInfo)) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(false);
        }
    }

    //아이디 중복 확인
    @GetMapping
    public ResponseEntity<UserDTO> idDuplicationCheck(@RequestParam String userId) {
        return null;
    }

    //회원정보 수정
    @PatchMapping
    public ResponseEntity<Boolean> patch(@AuthenticationPrincipal UserDTO userInfo,
                                         @RequestBody @Valid UserPatchDTO modifiedInfo) {
        return null;
    }

    //회원탈퇴
    @DeleteMapping
    public ResponseEntity<Boolean> withdraw(@AuthenticationPrincipal UserDTO userInfo) {
        return null;
    }
}
