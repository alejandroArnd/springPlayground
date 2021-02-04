package com.apispring.apispring.infrastructure.Security.service;

import com.apispring.apispring.domain.model.UserRole;
import com.apispring.apispring.infrastructure.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Collectors;

@Service
public class JwtService {

    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String TOKEN_TYPE = "JWT";
    private static final int EXPIRATION_TIME = 1000 * 60 * 60;
    private String JwtSecret = "Itisaperiodofcivilwarsinthegalaxy.AbraveallianceofundergroundfreedomfightershaschallengedthetyrannyandoppressionoftheawesomeGALACTICEMPIRE";

    public String createToken(Authentication authentication){
        User user = (User) authentication.getPrincipal();

        Date tokenExpirationDate = new Date(System.currentTimeMillis()+EXPIRATION_TIME);

        return Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(JwtSecret.getBytes()), SignatureAlgorithm.HS512)
                .setHeaderParam("typ",TOKEN_TYPE)
                .setSubject(Integer.toString(user.getId()))
                .setIssuedAt(new Date())
                .setExpiration(tokenExpirationDate)
                .claim("username", user.getUsername())
                .claim("roles",user.getRoles().stream().map(UserRole::name).collect(Collectors.joining(", ")))
                .compact();
    }

    public Boolean hasTokenExpired(String token){
        return Jwts.parserBuilder()
                .setSigningKey(JwtSecret.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }

    public Integer getIdOfUserFromJwt(String token){
        String userId = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(this.JwtSecret.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

        return Integer.parseInt(userId);
    }

    public Boolean validateToken(String token){
        try {
            Jwts.parserBuilder()
                    .setSigningKey(JwtSecret.getBytes())
                    .build()
                    .parseClaimsJws(token);

            return true;
        } catch (ExpiredJwtException e) {
            e.printStackTrace();

        } catch (UnsupportedJwtException e) {
            e.printStackTrace();

        } catch (MalformedJwtException e) {
            e.printStackTrace();

        } catch (SignatureException e) {
            e.printStackTrace();

        } catch (IllegalArgumentException e) {
            e.printStackTrace();

        }
        return false;
    }
}
