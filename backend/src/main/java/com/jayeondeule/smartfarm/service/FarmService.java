package com.jayeondeule.smartfarm.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jayeondeule.smartfarm.dto.farm.FarmInsertDTO;
import com.jayeondeule.smartfarm.entity.farm.Farm;
import com.jayeondeule.smartfarm.repository.FarmRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FarmService {
    private final FarmRepository farmRepository;
    private final ObjectMapper mapper;

    @PostConstruct
    public void init() {
        mapper.registerModule(new JavaTimeModule());
    }

    public void insertFarm(FarmInsertDTO farmInfo) {
        farmRepository.save(mapper.convertValue(farmInfo, Farm.class));
    }
}
