package com.jayeondeule.smartfarm.controller;

import com.jayeondeule.smartfarm.dto.user.UserDTO;
import com.jayeondeule.smartfarm.dto.user.UserPatchDTO;
import com.jayeondeule.smartfarm.dto.user.UserInsertDTO;
import com.jayeondeule.smartfarm.enums.user.AuthLvel;
import com.jayeondeule.smartfarm.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//회원가입, 로그인 등 사용자 관련 API 엔드포인트
@RestController
@RequestMapping("/api/user")
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
    public ResponseEntity<List<UserDTO>> getUser(@AuthenticationPrincipal UserDTO userInfo) {
        // admin일 경우에만 반환
        if (userInfo != null) {
            if (userInfo.getAuthLvel().equals(AuthLvel.ADMIN)) {
                return ResponseEntity.ok(userService.getUsers());
            }
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(null);
    }

    //아이디 중복 확인
    @GetMapping("/id-dupl-check/{userId}")
    public ResponseEntity<Boolean> idDuplicationCheck(@RequestParam String userId) {
        return ResponseEntity.ok(userService.idDuplicationCheck(userId));
    }

    //회원정보 수정
    @PatchMapping
    public void patchUser(@AuthenticationPrincipal UserDTO userInfo,
                          @RequestBody @Valid UserPatchDTO modifiedInfo) {

    }

    //회원탈퇴
    @DeleteMapping
    public void deleteUser(@AuthenticationPrincipal UserDTO userInfo) {

    }
}
