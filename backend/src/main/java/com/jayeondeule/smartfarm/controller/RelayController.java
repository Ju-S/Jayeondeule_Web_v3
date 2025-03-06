package com.jayeondeule.smartfarm.controller;

import com.jayeondeule.smartfarm.dto.FarmHouseDTO;
import com.jayeondeule.smartfarm.dto.RelayDTO;
import com.jayeondeule.smartfarm.service.RelayService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//릴레이 동장(수동 및 알고리즘 제어) API
@RestController
public class RelayController {

    private final RelayService relayService;

    public RelayController(RelayService relayService) {
        this.relayService = relayService;
    }

    //릴레이 동작 상태 조회(조명, 관수 및 시스템)
    @GetMapping("/getRelayInfo")
    public ResponseEntity<RelayDTO> getRelayInfo(@RequestParam FarmHouseDTO houseInfo) {
        return ResponseEntity.ok(relayService.getRelayInfo(houseInfo));
    }

    //수동 조작 상태에서의 릴레이 상태 변경
    @PostMapping("/updateManualRelay")
    public ResponseEntity<RelayDTO> updateManualRelay(@Valid @RequestBody RelayDTO relayInfo) {
        return ResponseEntity.ok(relayService.updateManualRelay(relayInfo));
    }
}
