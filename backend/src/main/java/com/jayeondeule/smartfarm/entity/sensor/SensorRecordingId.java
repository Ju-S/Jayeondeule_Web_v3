package com.jayeondeule.smartfarm.entity.sensor;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
public class SensorRecordingId implements Serializable {
    private Long farmId;
    private Long housId;
    private LocalDateTime recdDttm;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SensorRecordingId that)) return false;
        return Objects.equals(farmId, that.farmId) &&
                Objects.equals(housId, that.housId) &&
                Objects.equals(recdDttm, that.recdDttm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(farmId, housId, recdDttm);
    }
}
