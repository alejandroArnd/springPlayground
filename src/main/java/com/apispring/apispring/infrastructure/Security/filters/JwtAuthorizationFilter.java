package com.apispring.apispring.infrastructure.Security.filters;

import com.apispring.apispring.application.services.SecurityService;
import com.apispring.apispring.infrastructure.Security.service.JwtService;
import com.apispring.apispring.infrastructure.entity.User;
import com.apispring.apispring.infrastructure.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
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
    private final SecurityService securityService;
    private final UserMapper userMapper;

    @Override
    protected void doFilterInternal(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String token = getJwtFromRequest(httpServletRequest);

        if (token != null && this.jwtService.validateToken(token)){
            int userId = this.jwtService.getIdOfUserFromJwt(token);

            User user = this.userMapper.toEntity(this.securityService.loadUserById(userId));

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, user.getRoles(), user.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetails(httpServletRequest));

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private String getJwtFromRequest(HttpServletRequest httpServletRequest) {
        String bearerToken = httpServletRequest.getHeader(this.jwtService.TOKEN_HEADER);
        String tokenWithoutBearer = null;

        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(this.jwtService.TOKEN_PREFIX)){
            tokenWithoutBearer = bearerToken.substring(this.jwtService.TOKEN_PREFIX.length(), bearerToken.length());
        }
        return tokenWithoutBearer;
    }


}
