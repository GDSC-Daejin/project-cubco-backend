package org.cubco.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.cubco.exception.ErrorCode;
import org.cubco.response.CommonResponse;
import org.cubco.util.JWTutil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@PropertySource("classpath:security.properties")
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JWTutil jwTutil;

    @Value("${jwt.header}")
    private String authorizationHeader;

    private void handleAuthenticationError(HttpServletResponse response, CommonResponse<Void> errorResponse) throws IOException {
        response.setStatus(ErrorCode.UNAUTHORIZED.getHttpStatus().value());
        response.setContentType("application/json;charset=UTF-8");

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(errorResponse);
        response.getWriter().write(jsonResponse);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(authorizationHeader);

        if (token == null || !token.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            token = token.substring(7);
            Long userId = jwTutil.getUserId(token);
            String role = jwTutil.getRole(token);

            if (jwTutil.isExpired(token)) {
                CommonResponse<Void> errorResponse = CommonResponse.fail(
                        ErrorCode.TOKEN_EXPIRED.getCode()
                        ,ErrorCode.TOKEN_EXPIRED.getMessage());
                handleAuthenticationError(response, errorResponse);
                return;
            }

            List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(role));
            User authenticatedUser = new User(userId.toString(), "", authorities);

            var authenticationToken = new UsernamePasswordAuthenticationToken(authenticatedUser, null, authorities);
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        } catch (Exception e) {
            CommonResponse<Void> errorResponse = CommonResponse.fail(
                    ErrorCode.TOKEN_INVALID.getCode()
                    ,ErrorCode.TOKEN_INVALID.getMessage());
            handleAuthenticationError(response, errorResponse);
            return;
        }

        filterChain.doFilter(request, response);
    }
}
