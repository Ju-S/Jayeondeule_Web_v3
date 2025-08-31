package com.jayeondeule.smartfarm.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jayeondeule.smartfarm.dto.house.FarmHouseDTO;
import com.jayeondeule.smartfarm.dto.house.FarmHouseInsertDTO;
import com.jayeondeule.smartfarm.dto.house.FarmHousePatchDTO;
import com.jayeondeule.smartfarm.entity.house.FarmHouse;
import com.jayeondeule.smartfarm.entity.house.FarmHouseId;
import com.jayeondeule.smartfarm.repository.FarmHouseRepository;
import com.jayeondeule.smartfarm.repository.FarmRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
//재배사 등록, 설정, 모니터링 로직
public class HouseService {
    private final FarmHouseRepository farmHouseRepository;
    private final ObjectMapper mapper;

    @PostConstruct
    public void init() {
        mapper.registerModule(new JavaTimeModule());
    }

    public List<FarmHouseDTO> getHouseList(long farmId) {
        List<FarmHouse> result = farmHouseRepository.findAllByFarmId(farmId);
        return result.stream().map(item -> mapper.convertValue(item, FarmHouseDTO.class)).toList();
    }

    public FarmHouseDTO getHouse(long farmId, long houseId) {
        FarmHouseId id = FarmHouseId.builder()
                .farmId(farmId)
                .housId(houseId)
                .build();

        Optional<FarmHouse> targetOpt = farmHouseRepository.findById(id);

        return targetOpt.map(farmHouse -> mapper.convertValue(farmHouse, FarmHouseDTO.class)).orElse(null);
    }

    public void insertHouse(FarmHouseInsertDTO insertInfo) {
        // 해당 farm의 house갯수에 따라 hous_id 지정.
        insertInfo.setHousId(farmHouseRepository.findAllByFarmId(insertInfo.getFarmId()).size() + 1);
        farmHouseRepository.save(mapper.convertValue(insertInfo, FarmHouse.class));
    }

    public void patchHouse(FarmHousePatchDTO modifiedInfo, long farmId, long houseId) {
        FarmHouseId id = FarmHouseId.builder()
                .farmId(farmId)
                .housId(houseId)
                .build();

        Optional<FarmHouse> targetOpt = farmHouseRepository.findById(id);

        if(targetOpt.isPresent()) {
            FarmHouse target = targetOpt.get();

            target.setHousName(modifiedInfo.getHousName());
            target.setCropKind(modifiedInfo.getCropKind());

            farmHouseRepository.save(target);
        }
    }

    public void deleteHouse(long farmId, long houseId) {
        FarmHouseId id = FarmHouseId.builder()
                .farmId(farmId)
                .housId(houseId)
                .build();

        Optional<FarmHouse> targetOpt = farmHouseRepository.findById(id);

        if(targetOpt.isPresent()) {
            FarmHouse target = targetOpt.get();
            farmHouseRepository.delete(target);
        }
    }
}
