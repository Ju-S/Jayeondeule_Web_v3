package com.jayeondeule.smartfarm.controller;

import com.jayeondeule.smartfarm.dto.relay.RelayDTO;
import com.jayeondeule.smartfarm.dto.user.UserDTO;
import com.jayeondeule.smartfarm.service.RelayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

//릴레이 동장(수동 및 알고리즘 제어) API
@RestController
@RequestMapping("/api/relay")
@RequiredArgsConstructor
public class RelayController {
    private final RelayService relayService;

    //릴레이 동작 상태 조회
    @GetMapping
    public ResponseEntity<RelayDTO> getRelay(@RequestParam String houseId,
                                             @AuthenticationPrincipal UserDTO userInfo) {
        return null;
    }

    //릴레이 상태 변경
    @PostMapping
    public ResponseEntity<Boolean> patchRelay(@RequestBody RelayDTO relayInfo,
                                               @RequestParam String houseId,
                                               @AuthenticationPrincipal UserDTO userInfo) {
        return null;
    }
}
