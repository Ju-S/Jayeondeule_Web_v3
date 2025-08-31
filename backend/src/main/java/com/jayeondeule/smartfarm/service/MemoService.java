package com.jayeondeule.smartfarm.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jayeondeule.smartfarm.dto.farm.FarmDTO;
import com.jayeondeule.smartfarm.dto.memo.FarmHouseCropsDTO;
import com.jayeondeule.smartfarm.dto.memo.FarmHouseCropsInsertDTO;
import com.jayeondeule.smartfarm.dto.user.UserClaimDTO;
import com.jayeondeule.smartfarm.entity.farm.Farm;
import com.jayeondeule.smartfarm.entity.memo.FarmHouseCrops;
import com.jayeondeule.smartfarm.entity.memo.FarmHouseCropsId;
import com.jayeondeule.smartfarm.repository.FarmHouseCropsRepository;
import com.jayeondeule.smartfarm.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
//재배사 등록, 설정, 모니터링 로직
public class MemoService {
    private final FarmHouseCropsRepository farmHouseCropsRepository;
    private final UserRepository userRepository;
    private final ObjectMapper mapper;

    @PostConstruct
    public void init() {
        mapper.registerModule(new JavaTimeModule());
    }

    public void insertMemo(long farmId, long houseId, FarmHouseCropsInsertDTO insertInfo, UserClaimDTO author) {
        FarmHouseCrops data = mapper.convertValue(insertInfo, FarmHouseCrops.class);
        data.setFarmId(farmId);
        data.setHousId(houseId);
        data.setAthr(author.getUserId());
        data.setRmks(insertInfo.getMemo());

        farmHouseCropsRepository.save(data);
    }

    public Page<FarmHouseCropsDTO> getMemos(long farmId, long houseId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("recdDttm").descending());

        Page<FarmHouseCrops> memoList = farmHouseCropsRepository.findAllByFarmIdAndHousId(farmId, houseId, pageable);

        Page<FarmHouseCropsDTO> result = memoList.map(memo -> mapper.convertValue(memo, FarmHouseCropsDTO.class));
        result.forEach(item -> item.setAthrName(userRepository.findByUserId(item.getAthr()).getUserName()));
        return result;
    }

    public void patchMemo(long farmId, long houseId, LocalDateTime recdDttm, String memo) {
        FarmHouseCropsId id = FarmHouseCropsId.builder()
                .farmId(farmId)
                .housId(houseId)
                .recdDttm(recdDttm)
                .build();

        Optional<FarmHouseCrops> opt = farmHouseCropsRepository.findById(id);

        if(opt.isPresent()) {
            FarmHouseCrops target = opt.get();
            target.setRmks(memo);
            farmHouseCropsRepository.save(target);
        }
    }

    public void deleteMemo(long farmId, long houseId, LocalDateTime recdDttm) {
        FarmHouseCropsId id = FarmHouseCropsId.builder()
                .farmId(farmId)
                .housId(houseId)
                .recdDttm(recdDttm)
                .build();

        Optional<FarmHouseCrops> opt = farmHouseCropsRepository.findById(id);

        if(opt.isPresent()) {
            farmHouseCropsRepository.delete(opt.get());
        }
    }
}
