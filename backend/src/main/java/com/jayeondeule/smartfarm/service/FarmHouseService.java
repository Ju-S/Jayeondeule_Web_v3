package com.jayeondeule.smartfarm.service;

import com.jayeondeule.smartfarm.dto.*;
import com.jayeondeule.smartfarm.model.FarmHouse;
import org.springframework.http.ResponseEntity;

//재배사 등록, 설정, 모니터링 로직
public class FarmHouseService {

    //재배사 등록
    public FarmHouseDTO farmHouseRegister(FarmHouseRegisterDTO farmRegisterInfo) {
        FarmHouse farmHouse = new FarmHouse();

        //TODO: farmRegisterInfo의 내용을 토대로 재배사 등록 후 FarmHouseDTO형식으로 반환

        return FarmHouseDTO;
    }

    //재배사 메모 저장
    public FarmHouseMemoDTO saveFarmHouseMemo(FarmHouseMemoDTO memo) {

        //TODO: memo의 내용을 토대로 메모 등록 후 FarmHouseMemoDTO형식으로 반환

        return FarmHouseMemoDTO;
    }

    //재배사 수동 조작 flag update
    public FarmHouseManualFlagDTO updateManualFlag(FarmHouseManualFlagDTO updateFlagInfo) {

        //TODO: updateFlagInfo의 내용을 토대로 수동 조작 업데이트 후 FarmHouseManualFlagDTO 형식으로 반환

        return FarmHouseManualFlagDTO;
    }

    //재배사 센서 측정 시간 간격 설정
    public SensorIntervalUpdateDTO updateSensorInterval(SensorIntervalUpdateDTO sensorInterval) {

        //TODO: sensorInterval의 내용을 토대로 센서 측정 간격 업데이트 후 SensorIntervalUpdateDTO 형식으로 반환

        return SensorIntervalUpdateDTO;
    }
}
