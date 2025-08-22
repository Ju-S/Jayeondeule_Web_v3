package com.jayeondeule.smartfarm.filter;

import com.jayeondeule.smartfarm.enums.security.WhiteList;
import com.jayeondeule.smartfarm.dto.user.UserDTO;
import com.jayeondeule.smartfarm.util.JwtUtil;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException, java.io.IOException {

        String path = request.getRequestURI();
        String method = request.getMethod();
        String header = request.getHeader("Authorization");

        if (WhiteList.contains(path, method)) {
            filterChain.doFilter(request, response);
            return;
        }

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            try {
                // JWT에서 Map 형태의 사용자 정보 추출
                UserDTO userInfo = jwtUtil.getUserInfo(token);

                System.out.println(userInfo.getAuthLvel());
                System.out.println(token);

                // Authentication 객체 생성
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(
                                userInfo, // principal: 사용자 정보(Map)
                                null,     // credentials
                                List.of() // authorities: 나중에 Role 기반 권한 처리 가능
                        );

                SecurityContextHolder.getContext().setAuthentication(auth);

            } catch (Exception e) {
                // 토큰 유효하지 않으면 401
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }

        // 필터 체인 계속 진행
        filterChain.doFilter(request, response);
    }
}
