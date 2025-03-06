package com.jayeondeule.smartfarm.service;

import com.jayeondeule.smartfarm.dto.FarmHouseDTO;
import com.jayeondeule.smartfarm.dto.RelayDTO;

public class RelayService {

    //릴레이 동작 상태 조회(조명, 관수 및 시스템)
    public RelayDTO getRelayInfo(FarmHouseDTO houseInfo) {

        //TODO: houseInfo에 맞는 최신 릴레이 상태 조회

        return RelayDTO;
    }

    //수동 조작 상태에서의 릴레이 상태 변경
    public RelayDTO updateManualRelay(RelayDTO relayInfo) {

        //TODO: relayInfo에 따라 릴레이 DB update

        return RelayDTO;
    }
}
