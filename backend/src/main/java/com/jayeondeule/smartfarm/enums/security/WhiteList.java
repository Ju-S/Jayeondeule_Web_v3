package com.jayeondeule.smartfarm.enums.security;

import lombok.Getter;

@Getter
public enum WhiteList {
    LOGIN("/api/auth", "POST"),
    REGISTER("/api/users", "POST"),
    ID_DUPLICATION_CHECK("/api/users/id-dupl-check**", "GET"),
    ERROR("/error", "GET"),
    SERVER_TIME("/serverTime", "GET");


    private final String path;
    private final String method;

    WhiteList(String path, String method) {
        this.path = path;
        this.method = method;
    }

    // 편의 메서드: URI가 whitelist에 포함되는지 확인
    public static boolean contains(String uri, String method) {
        return java.util.Arrays.stream(WhiteList.values())
                .anyMatch(w -> uri.equals(w.getPath()) && method.equalsIgnoreCase(w.getMethod()));
    }
}
