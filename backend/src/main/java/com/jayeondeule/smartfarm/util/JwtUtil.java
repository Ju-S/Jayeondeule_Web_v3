package com.jayeondeule.smartfarm.util;

import com.jayeondeule.smartfarm.dto.user.UserClaimDTO;
import com.jayeondeule.smartfarm.enums.user.AuthLvel;
import com.jayeondeule.smartfarm.dto.user.UserDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {

    private final Key key;
//    private final long accessTokenValidity = 1000L * 60 * 60 * 2; // 2시간
    private final long accessTokenValidity = 1000L * 60 * 60 * 24 * 7; // 7일(임시)
//    private final long refreshTokenValidity = 1000L * 60 * 60 * 24 * 7; // 7일

    public JwtUtil(@Value("${jwt.secret}") String secretKey) {
        key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    // 사용자 객체(맵 형태)로 토큰 생성
    public String generateToken(Map<String, Object> userInfo) {
        return Jwts.builder()
                .claims(userInfo)  // user 객체를 claim 으로 저장
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + accessTokenValidity))
                .signWith(key)
                .compact();
    }

    // 토큰에서 Claims 꺼내기
    public Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // 사용자 정보 복원
    public UserClaimDTO getUserInfo(String token) {
        Claims claims = getClaims(token);

        return UserClaimDTO.builder()
                .userId(claims.get("userId").toString())
                .authLvel(AuthLvel.valueOf(claims.get("authLvel").toString()))
                .build();
    }
}
