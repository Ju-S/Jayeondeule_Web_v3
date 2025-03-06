package com.jayeondeule.smartfarm.service;

import com.jayeondeule.smartfarm.dto.FarmDTO;
import com.jayeondeule.smartfarm.dto.FarmRegisterDTO;
import com.jayeondeule.smartfarm.model.Farm;

import java.util.List;

public class FarmService {
    //농장 등록/수정 로직

    //선택된 page에 해당하는 농장 리스트 반환
    public List<FarmDTO> getFarmsPage(String page) {
        List<FarmDTO> farmDTOList;

        //TODO: 등록된 농장들에서 선택된 page에 해당하는 농장들 return

        return farmDTOList;
    }

    //농장 등록
    public FarmDTO farmRegister(FarmRegisterDTO farmRegisterInfo) {
        Farm farm = new Farm();

        //TODO: farmRegisterInfo의 내용을 토대로 농장 등록 후 FarmDTO 형식으로 반환

        return FarmDTO;
    }
}
