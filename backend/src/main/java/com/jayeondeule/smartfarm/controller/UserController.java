package com.jayeondeule.smartfarm.controller;

import com.jayeondeule.smartfarm.dto.*;
import com.jayeondeule.smartfarm.service.RelayService;
import com.jayeondeule.smartfarm.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//회원가입, 로그인 등 사용자 관련 API 엔드포인트
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //회원가입
    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signup(@RequestParam UserDTO signupInfo) {
        return ResponseEntity.ok(userService.signup(signupInfo));
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestParam LoginDTO loginInfo) {
        return ResponseEntity.ok(userService.login(loginInfo));
    }

    //아이디 중복 확인
    @PostMapping("/idDuplicationCheck")
    public ResponseEntity<IdDuplicationCheckDTO> idDuplicationCheck(@RequestParam IdDuplicationCheckDTO idInfo) {
        return ResponseEntity.ok(userService.idDuplicationCheck(idInfo));
    }
}
