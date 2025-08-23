package com.jayeondeule.smartfarm.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jayeondeule.smartfarm.dto.house.FarmHouseDTO;
import com.jayeondeule.smartfarm.entity.house.FarmHouse;
import com.jayeondeule.smartfarm.repository.FarmHouseRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
