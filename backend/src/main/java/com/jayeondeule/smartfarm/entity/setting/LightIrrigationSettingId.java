package com.jayeondeule.smartfarm.entity.setting;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LightIrrigationSettingId implements Serializable {
    private Long farmId;
    private Long housId;
    private LocalDateTime setnDttm;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LightIrrigationSettingId that)) return false;
        return Objects.equals(farmId, that.farmId) &&
                Objects.equals(housId, that.housId) &&
                Objects.equals(setnDttm, that.setnDttm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(farmId, housId, setnDttm);
    }
}
