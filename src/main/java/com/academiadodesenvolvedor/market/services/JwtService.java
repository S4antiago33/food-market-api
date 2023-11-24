package com.academiadodesenvolvedor.market.services;

import com.academiadodesenvolvedor.market.models.User;
import com.academiadodesenvolvedor.market.services.contracts.JwtServiceContract;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

@Service
public class JwtService implements JwtServiceContract {
    @Value("${app.jwt.secret}")
    private String secret;

    @Value("${app.jwt.expiration}")
    private String expiration;

    @Override
    public Algorithm getAlgorithm() throws Exception {
        return Algorithm.HMAC256(this.secret);
    }

    @Override
    public DecodedJWT decode(String jwt) throws Exception {
        return this.getVerifier().verify(jwt);
    }

    @Override
    public String encode(User user) throws Exception {
        LocalDateTime expirationTime = LocalDateTime.now()
                .plusMinutes(Long.parseLong(this.expiration));
        Date expiration = Date.from(expirationTime
                .atZone(ZoneId.systemDefault()).toInstant());

        return JWT.create()
                .withJWTId(UUID.randomUUID().toString())
                .withExpiresAt(expiration)
                .withSubject(user.getId().toString())
                .withClaim("name",user.getName())
                .withClaim("email",user.getEmail())
                .sign(this.getAlgorithm());
    }

    @Override
    public JWTVerifier getVerifier() throws Exception {
        return JWT.require(this.getAlgorithm()).build();
    }

    @Override
    public boolean isTokenValid(String jwt){
        try{
            DecodedJWT claims = this.decode(jwt);
            LocalDateTime dateExpiration = claims.getExpiresAt()
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();

            return !LocalDateTime.now().isAfter(dateExpiration);
        }catch (Exception e){
            return false;
        }
    }
}
