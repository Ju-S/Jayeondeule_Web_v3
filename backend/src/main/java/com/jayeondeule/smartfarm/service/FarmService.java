package com.jayeondeule.smartfarm.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jayeondeule.smartfarm.dto.farm.FarmDTO;
import com.jayeondeule.smartfarm.dto.farm.FarmInsertDTO;
import com.jayeondeule.smartfarm.entity.farm.Farm;
import com.jayeondeule.smartfarm.entity.user.User;
import com.jayeondeule.smartfarm.repository.FarmRepository;
import com.jayeondeule.smartfarm.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FarmService {
    private final FarmRepository farmRepository;
    private final UserRepository userRepository;
    private final ObjectMapper mapper;

    @PostConstruct
    public void init() {
        mapper.registerModule(new JavaTimeModule());
    }

    public void insertFarm(FarmInsertDTO farmInfo) {
        farmRepository.save(mapper.convertValue(farmInfo, Farm.class));
    }

    public Page<FarmDTO> getAllFarms(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("rgstDttm").descending());
        System.out.println(farmRepository.findAllBy(pageable));

        Page<Farm> farmList = farmRepository.findAllBy(pageable);

        return farmList.map(farm -> mapper.convertValue(farm, FarmDTO.class));
    }

    public Page<FarmDTO> getFarmsByUserId(int page, int size, String userId) {
        User owner = userRepository.findByUserId(userId);

        Pageable pageable = PageRequest.of(page, size, Sort.by("rgstDttm").descending());
        List<Farm> userFarm = farmRepository.findByFarmId(owner.getFarmId());

        List<FarmDTO> userFarmDTO = userFarm.stream().map(
                farm -> mapper.convertValue(farm, FarmDTO.class)
        ).toList();

        return new PageImpl<>(userFarmDTO, pageable, userFarm.size());
    }
}
