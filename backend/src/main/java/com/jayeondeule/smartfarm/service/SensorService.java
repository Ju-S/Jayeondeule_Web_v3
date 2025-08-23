package com.jayeondeule.smartfarm.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jayeondeule.smartfarm.dto.sensor.SensorDataDTO;
import com.jayeondeule.smartfarm.entity.sensor.SensorRecording;
import com.jayeondeule.smartfarm.repository.SensorRecordingRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SensorService {
    private final SensorRecordingRepository sensorRecordingRepository;
    private final ObjectMapper mapper;

    @PostConstruct
    public void init() {
        mapper.registerModule(new JavaTimeModule());
    }

    //start~end까지의 sensor데이터 조회
    public List<SensorDataDTO> getSensorHistory(long farmId, long houseId, LocalDateTime start, LocalDateTime end) {
        List<SensorRecording> history = sensorRecordingRepository.findByFarmIdAndHousIdAndRecdDttmBetween(farmId, houseId, start, end);
        return history.stream().map(item -> mapper.convertValue(item, SensorDataDTO.class)).toList();
    }
}
