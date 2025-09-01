package com.jayeondeule.smartfarm.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jayeondeule.smartfarm.dto.setting.*;
import com.jayeondeule.smartfarm.entity.setting.LightIrrigationSetting;
import com.jayeondeule.smartfarm.entity.setting.LightIrrigationSettingId;
import com.jayeondeule.smartfarm.entity.setting.SensorSetting;
import com.jayeondeule.smartfarm.repository.LightIrrigationSettingRepository;
import com.jayeondeule.smartfarm.repository.SensorSettingRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SettingService {
    private final SensorSettingRepository sensorSettingRepository;
    private final LightIrrigationSettingRepository lightIrrigationSettingRepository;

    private final ObjectMapper mapper;

    @PostConstruct
    public void init() {
        mapper.registerModule(new JavaTimeModule());
    }

    // 설정 값 조회
    public SettingDTO getSettings(long farmId, long houseId) {
        SensorSettingDTO sensorSettingDTO = mapper.convertValue(sensorSettingRepository.findTopByFarmIdAndHousIdOrderBySetnDttmDesc(farmId, houseId), SensorSettingDTO.class);
        List<LightIrrigationSettingDTO> lightIrrigationSettingDTOList =
                lightIrrigationSettingRepository.findAllByFarmIdAndHousIdOrderByStrtTime(farmId, houseId).stream().map(item ->
                        mapper.convertValue(item, LightIrrigationSettingDTO.class)).toList();

        return SettingDTO.builder()
                .sensorSettingDTO(sensorSettingDTO)
                .lightIrrigationSettingDTO(lightIrrigationSettingDTOList)
                .build();
    }

    // 설정 값 입력
    public void insertSettings(long farmId, long houseId, SettingInsertDTO insertInfo) {
        SensorSetting inputSensorSetting =
                mapper.convertValue(insertInfo.getSensorSettingInsertDTO(), SensorSetting.class);
        LightIrrigationSetting lightIrrigationSetting =
                mapper.convertValue(insertInfo.getLightIrrigationSettingInsertDTO(), LightIrrigationSetting.class);

        if(inputSensorSetting != null) {
            inputSensorSetting.setFarmId(farmId);
            inputSensorSetting.setHousId(houseId);
            sensorSettingRepository.save(inputSensorSetting);
        }
        if(lightIrrigationSetting != null) {
            lightIrrigationSetting.setFarmId(farmId);
            lightIrrigationSetting.setHousId(houseId);
            lightIrrigationSettingRepository.save(lightIrrigationSetting);
        }
    }

    // 설정 값 삭제(조명/관수)
    public void deleteSetting(long farmId, long houseId, LocalDateTime setnDttm) {
        LightIrrigationSettingId id = LightIrrigationSettingId.builder()
                .farmId(farmId)
                .housId(houseId)
                .setnDttm(setnDttm)
                .build();

        Optional<LightIrrigationSetting> target = lightIrrigationSettingRepository.findById(id);
        if(target.isPresent()) {
            lightIrrigationSettingRepository.delete(target.get());
        }
    }

    public void patchSetting(long farmId, long houseId, LocalDateTime setnDttm, LightIrrigationSettingPatchDTO modifiedInfo) {
        LightIrrigationSettingId id = LightIrrigationSettingId.builder()
                .farmId(farmId)
                .housId(houseId)
                .setnDttm(setnDttm)
                .build();

        Optional<LightIrrigationSetting> target = lightIrrigationSettingRepository.findById(id);
        if(target.isPresent()) {
            LightIrrigationSetting data = target.get();

            data.setDlteYn(modifiedInfo.isDlteYn());
            data.setStrtTime(modifiedInfo.getStrtTime());
            data.setFnshTime(modifiedInfo.getFnshTime());

            lightIrrigationSettingRepository.save(data);
        }
    }
}
