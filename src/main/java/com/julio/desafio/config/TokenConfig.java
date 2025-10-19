package com.julio.desafio.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.julio.desafio.entity.User;
import com.julio.desafio.enums.Role;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Component
public class TokenConfig {


    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user){
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                .withClaim("userId",user.getId())
                .withClaim("roles",user.getRoles().stream().map(Enum::name).toList())
                .withSubject(user.getEmail())
                .withExpiresAt(Instant.now().plusSeconds(86400))
                .withIssuedAt(Instant.now())
                .sign(algorithm);
    }

    public Optional<JWTUserData> validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            DecodedJWT decode = JWT.require(algorithm)
                    .build().verify(token);

            Long userId = decode.getClaim("userId").asLong();
            String email = decode.getSubject();
            List<String> roles = decode.getClaim("roles").asList(String.class);
            JWTUserData userData = new JWTUserData(userId, email,roles);

            return Optional.of(userData);

        } catch (JWTVerificationException ex) {
            return Optional.empty();
        }
    }
}
