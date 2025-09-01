package com.jayeondeule.smartfarm.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jayeondeule.smartfarm.dto.sensor.SensorDataDTO;
import com.jayeondeule.smartfarm.entity.sensor.SensorRecording;
import com.jayeondeule.smartfarm.repository.SensorRecordingRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
        long maxCount = 1000;
        List<Object[]> raw = sensorRecordingRepository.findAveragedDownsampled(farmId, houseId, start, end, maxCount);

        return raw.stream()
                .map(r -> SensorDataDTO.builder()
                        .recdDttm(((Timestamp) r[0]).toLocalDateTime())
                        .indrTprtValu(Double.parseDouble(r[1].toString()))
                        .indrHmdtValu(Double.parseDouble(r[2].toString()))
                        .oudrTprtValu(Double.parseDouble(r[3].toString()))
                        .oudrHmdtValu(Double.parseDouble(r[4].toString()))
                        .co2Valu(Double.parseDouble(r[5].toString()))
                        .watrTprtValu(Double.parseDouble(r[6].toString()))
                        .build())
                .toList();
    }

    public SensorDataDTO getLatestSensor(long farmId, long houseId) {
        return mapper.convertValue(sensorRecordingRepository.findTopByFarmIdAndHousIdOrderByRecdDttmDesc(farmId, houseId), SensorDataDTO.class);
    }
}
