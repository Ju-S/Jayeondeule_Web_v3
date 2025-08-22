package com.jayeondeule.smartfarm.entity.setting;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
public class SensorSettingId implements Serializable {
    private Long farmId;
    private Long housId;
    private LocalDateTime setnDttm;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SensorSettingId that)) return false;
        return Objects.equals(farmId, that.farmId) &&
                Objects.equals(housId, that.housId) &&
                Objects.equals(setnDttm, that.setnDttm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(farmId, housId, setnDttm);
    }
}
