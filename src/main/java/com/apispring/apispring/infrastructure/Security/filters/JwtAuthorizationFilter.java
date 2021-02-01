package com.apispring.apispring.infrastructure.Security.filters;

import com.apispring.apispring.application.services.ServiceSecurity;
import com.apispring.apispring.infrastructure.Security.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final ServiceSecurity serviceSecurity;

    @Override
    protected void doFilterInternal(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String token = getJwtFromRequest(httpServletRequest);

        if (token != null && jwtService.validateToken(token)){

        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private String getJwtFromRequest(HttpServletRequest httpServletRequest) {
        String bearerToken = httpServletRequest.getHeader(JwtService.TOKEN_HEADER);
        String tokenWithoutBearer = null;

        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(JwtService.TOKEN_PREFIX)){
            tokenWithoutBearer = bearerToken.substring(JwtService.TOKEN_PREFIX.length(), bearerToken.length());
        }
        return tokenWithoutBearer;
    }


}
