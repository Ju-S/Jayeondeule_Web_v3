package com.jayeondeule.smartfarm.entity.house;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
public class FarmHouseId implements Serializable {
    private Long farmId;
    private Long housId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FarmHouseId that)) return false;
        return Objects.equals(farmId, that.farmId) &&
                Objects.equals(housId, that.housId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(farmId, housId);
    }
}
