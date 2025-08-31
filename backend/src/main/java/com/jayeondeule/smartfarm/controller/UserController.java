package com.jayeondeule.smartfarm.controller;

import com.jayeondeule.smartfarm.dto.user.*;
import com.jayeondeule.smartfarm.enums.user.AuthLvel;
import com.jayeondeule.smartfarm.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//회원가입, 로그인 등 사용자 관련 API 엔드포인트
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    //회원가입
    @PostMapping
    public void insertUser(@RequestBody @Valid UserInsertDTO userInfo) {
        userService.insertUser(userInfo);
    }

    //아이디 회원정보 리스트 조회
    @GetMapping
    public ResponseEntity<Page<UserDTO>> getUsers(@AuthenticationPrincipal UserClaimDTO userInfo,
                                                  @RequestParam int page,
                                                  @RequestParam int size,
                                                  @RequestParam long searchQuery) {
        // admin일 경우에만 반환
        if (userInfo != null) {
            if (userInfo.getAuthLvel().equals(AuthLvel.ADMIN)) {
                if(searchQuery == -1) {
                    return ResponseEntity.ok(userService.getUsers(page, size));
                } else {
                    return ResponseEntity.ok(userService.getUsers(page, size, searchQuery));
                }
            }
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(null);
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getUser(@AuthenticationPrincipal UserClaimDTO userInfo) {
        if (userInfo != null) {
            return ResponseEntity.ok(userService.getUser(userInfo.getUserId()));
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(null);
    }

    //아이디 중복 확인
    @GetMapping("/id-dupl-check")
    public ResponseEntity<Boolean> idDuplicationCheck(@RequestParam String userId) {
        return ResponseEntity.ok(userService.idDuplicationCheck(userId));
    }

    //회원정보 수정
    @PatchMapping
    public void patchUser(@AuthenticationPrincipal UserClaimDTO userInfo,
                          @RequestBody @Valid UserPatchDTO modifiedInfo) {
        if (userInfo != null) {
            userService.patchUser(modifiedInfo, userInfo.getUserId());
        }
    }

    //회원 농장 접근 권한 부여
    @PatchMapping("/{userId}")
    public void patchUserFarmId(@AuthenticationPrincipal UserClaimDTO userInfo,
                                @PathVariable String userId,
                                @RequestBody @Valid UserFarmIdPatchDTO modifiedInfo) {
        if (userInfo != null) {
            if (userInfo.getAuthLvel().equals(AuthLvel.ADMIN)) {
                userService.patchUserFarmId(modifiedInfo, userId);
            }
        }
    }

    //비밀번호 변경
    @PatchMapping("/password")
    public ResponseEntity<String> patchUserPassword(@AuthenticationPrincipal UserClaimDTO userInfo,
                                                    @RequestBody UserPasswordPatchDTO passwordInfo) {
        if (userInfo != null) {
            if (userService.patchUserPassword(passwordInfo, userInfo.getUserId())) {
                return ResponseEntity.ok(null);
            } else {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("현재 비밀번호가 일치하지 않습니다.");
            }
        } else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Invalid Token.");
        }
    }

    //회원탈퇴
    @DeleteMapping
    public void deleteUser(@AuthenticationPrincipal UserClaimDTO userInfo) {
        if(userInfo != null) {
            userService.deleteUser(userInfo.getUserId());
        }
    }
}
