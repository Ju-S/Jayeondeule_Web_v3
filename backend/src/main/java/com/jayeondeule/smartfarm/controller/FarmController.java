package com.jayeondeule.smartfarm.controller;

import com.jayeondeule.smartfarm.dto.FarmDTO;
import com.jayeondeule.smartfarm.dto.FarmRegisterDTO;
import com.jayeondeule.smartfarm.service.FarmService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//농장 등록, 수정, 조회 관련 API
@RestController
public class FarmController {

    private final FarmService farmService;

    public FarmController(FarmService farmService) {
        this.farmService = farmService;
    }

    //어드민 페이지 등록 농장 리스트
    @GetMapping("/adminPage")
    public ResponseEntity<List<FarmDTO>> adminPageGetList(@RequestParam String page) {
        //farmDTOS 리스트에 현재 DB에 등록된 농장의 정보를 담아서 return
        return ResponseEntity.ok(farmService.getFarmsPage(page));
    }

    //농장 등록
    @PostMapping("/farmRegister")
    public ResponseEntity<FarmDTO> farmRegister(@Valid @RequestBody FarmRegisterDTO farmRegisterInfo){
        //농장 등록 후, 등록 성공 여부 및 등록된 농장 데이터 반환
        return ResponseEntity.ok(farmService.farmRegister(farmRegisterInfo));
    }
}
