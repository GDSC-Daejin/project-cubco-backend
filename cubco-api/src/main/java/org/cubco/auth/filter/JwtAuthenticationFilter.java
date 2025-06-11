package org.cubco.auth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.cubco.exception.ErrorCode;
import org.cubco.response.CommonResponse;
import org.cubco.auth.jwt.JWTUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;

    public JwtAuthenticationFilter(JWTUtil jwTutil) {
        this.jwtUtil = jwTutil;
    }

    private void handleAuthenticationError(HttpServletResponse response, CommonResponse<?> errorResponse) throws IOException {
        response.setStatus(ErrorCode.UNAUTHORIZED.getHttpStatus().value());
        response.setContentType("application/json;charset=UTF-8");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(errorResponse);
        response.getWriter().write(jsonResponse);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = jwtUtil.extractToken(request);

            if (token == null) {
                filterChain.doFilter(request, response);
                return;
            }

            String userId = jwtUtil.getUserId(token);
            String role = jwtUtil.getRole(token);

            if (jwtUtil.isExpired(token)) {
                CommonResponse<?> errorResponse = CommonResponse.createError("유효기간이 만료된 토큰입니다.");
                handleAuthenticationError(response, errorResponse);
                return;
            }

            List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(role));
            User authenticatedUser = new User(userId.toString(), "", authorities);

            var authenticationToken = new UsernamePasswordAuthenticationToken(authenticatedUser, null, authorities);
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            CommonResponse<?> errorResponse = CommonResponse.createError("유효하지 않은 토큰입니다.");
            handleAuthenticationError(response, errorResponse);
        }
    }
}
