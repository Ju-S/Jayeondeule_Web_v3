package com.jayeondeule.smartfarm.enums.security;

public enum WhiteList {
    LOGIN("/api/auth"),
    REGISTER("/api/users"),
    SWAGGER_UI("/swagger-ui/**"),
    API_DOCS("/v3/api-docs/**");

    private final String path;

    WhiteList(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    // 편의 메서드: URI가 whitelist에 포함되는지 확인
    public static boolean contains(String uri) {
        return java.util.Arrays.stream(WhiteList.values())
                .anyMatch(w -> uri.matches(w.getPath()));
    }
}
