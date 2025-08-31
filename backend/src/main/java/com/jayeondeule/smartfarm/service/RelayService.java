package com.jayeondeule.smartfarm.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayeondeule.smartfarm.dto.relay.RelayDTO;
import com.jayeondeule.smartfarm.dto.relay.RelayInsertDTO;
import com.jayeondeule.smartfarm.entity.relay.RelayRecording;
import com.jayeondeule.smartfarm.entity.relay.RelayRecordingId;
import com.jayeondeule.smartfarm.repository.RelayRecordingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RelayService {
    private final RelayRecordingRepository relayRecordingRepository;
    private final ObjectMapper mapper;

    public RelayDTO getRelay(Long farmId, Long houseId) {
        return mapper.convertValue(relayRecordingRepository.findTopByFarmIdAndHousIdOrderByRecdDttmDesc(farmId, houseId), RelayDTO.class);
    }

    public void insertRelay(Long farmId, Long houseId, RelayInsertDTO insertInfo) {
        RelayRecording data = mapper.convertValue(insertInfo, RelayRecording.class);
        data.setFarmId(farmId);
        data.setHousId(houseId);

        relayRecordingRepository.save(data);
    }
}
