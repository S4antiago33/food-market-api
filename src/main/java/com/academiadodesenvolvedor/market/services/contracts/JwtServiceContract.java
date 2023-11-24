package com.academiadodesenvolvedor.market.services.contracts;

import com.academiadodesenvolvedor.market.models.User;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public interface JwtServiceContract {
    Algorithm getAlgorithm() throws Exception;

    DecodedJWT decode(String jwt) throws Exception;

    String encode(User user) throws Exception;

    JWTVerifier getVerifier() throws Exception;

    boolean isTokenValid(String jwt);
}
