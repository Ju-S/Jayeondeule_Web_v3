package com.jayeondeule.smartfarm.enums.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthLvel {
    ADMIN(0, "ADMIN"),
    FARM_ADMIN(1, "FARM_ADMIN"),
    MONITOR(2, "MONITOR");

    private final int code;
    private final String name;

    // React ↔ Spring 통신 시 문자열로 변환
    @JsonValue
    public String getName() {
        return name;
    }

    // React → Spring (String → Enum)
    @JsonCreator
    public static AuthLvel from(String name) {
        for (AuthLvel level : values()) {
            if (level.name.equalsIgnoreCase(name)) {
                return level;
            }
        }
        throw new IllegalArgumentException("Unknown auth level: " + name);
    }
}
