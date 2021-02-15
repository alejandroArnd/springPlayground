package com.apispring.apispring.infrastructure.Security.service;

import com.apispring.apispring.domain.model.UserRole;
import com.apispring.apispring.infrastructure.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class JwtService {

    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String TOKEN_TYPE = "JWT";
    private static final int EXPIRATION_TIME = 1000 * 60 * 60;
    private RSAPublicKey rsaPublicKey;
    private RSAPrivateKey rsaPrivateKey;

    public JwtService() throws Exception {
        this.rsaPublicKey = this.readPublicKey("/usr/src/myapp/config/jwt/public.der");
        this.rsaPrivateKey = this.readPrivateKey("/usr/src/myapp/config/jwt/private.der");
    }

    public String createToken(Authentication authentication){
        User user = (User) authentication.getPrincipal();

        Date tokenExpirationDate = new Date(System.currentTimeMillis()+EXPIRATION_TIME);

        return Jwts.builder()
                .signWith(this.rsaPrivateKey, SignatureAlgorithm.RS256)
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
                .setSigningKey(this.rsaPublicKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }

    public Integer getIdOfUserFromJwt(String token){
        String userId = Jwts.parserBuilder()
                .setSigningKey(this.rsaPublicKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

        return Integer.parseInt(userId);
    }

    public Boolean validateToken(String token){
        try {
            Jwts.parserBuilder()
                    .setSigningKey(this.rsaPublicKey)
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

    public RSAPrivateKey readPrivateKey(String filename) throws Exception {

        byte[] keyBytes = Files.readAllBytes(Paths.get(filename));

        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return (RSAPrivateKey) keyFactory.generatePrivate(pkcs8EncodedKeySpec);
    }

    public RSAPublicKey readPublicKey(String filename) throws Exception{

        byte[] keyBytes = Files.readAllBytes(Paths.get(filename));

        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return (RSAPublicKey) keyFactory.generatePublic(x509EncodedKeySpec);
    }
}
