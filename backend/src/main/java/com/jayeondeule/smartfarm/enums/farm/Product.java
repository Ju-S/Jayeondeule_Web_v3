package com.jayeondeule.smartfarm.enums.farm;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Product {
    MESHIMA(10); // 상황버섯

    private final int prdt;

    @JsonValue
    public int getPrdt() {
        return prdt;
    }

    // 숫자 값으로 enum 생성
    @JsonCreator
    public static Product fromPrdt(int prdt) {
        for (Product p : Product.values()) {
            if (p.prdt == prdt) return p;
        }
        throw new IllegalArgumentException("Invalid Product code: " + prdt);
    }
}
