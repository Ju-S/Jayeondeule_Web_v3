package com.jayeondeule.smartfarm.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayeondeule.smartfarm.dto.auth.LoginDTO;
import com.jayeondeule.smartfarm.dto.user.UserPatchDTO;
import com.jayeondeule.smartfarm.dto.user.UserDTO;
import com.jayeondeule.smartfarm.service.AuthService;
import com.jayeondeule.smartfarm.util.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final JwtUtil jwtUtil;

    //로그인
    @PostMapping
    public ResponseEntity<Map<String, Object>> login(@RequestBody @Valid LoginDTO loginInfo) {
        try {
            // 로그인 처리
            UserPatchDTO userInfo = authService.login(loginInfo);

            // JWT 발급
            ObjectMapper mapper = new ObjectMapper();
            String token = jwtUtil.generateToken(mapper.convertValue(userInfo, Map.class));

            return ResponseEntity.ok(Map.of(
                    "token", token,
                    "userInfo", userInfo
            ));
        } catch(RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", e.getMessage()));
        }
    }

    //security context의 userInfo를 불러와 인증된 사용자 정보 조회
    @GetMapping
    public ResponseEntity<UserDTO> getUserByToken(@AuthenticationPrincipal UserDTO userInfo) {
        if(userInfo != null) {
            return ResponseEntity.ok(userInfo);
        } else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }
    }
}
