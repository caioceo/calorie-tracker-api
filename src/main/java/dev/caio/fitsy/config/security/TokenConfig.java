package dev.caio.fitsy.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import dev.caio.fitsy.model.user.User;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TokenConfig {

    private final String secret = "khsdasvdbancas-o00i89wyubdjhas";

    public String generateToken(User user){
        try{
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                .withIssuer("fitsy-api")
                .withSubject(user.getEmail())
                .withExpiresAt(Instant.now().plusSeconds(2592000))
                .withIssuedAt(Instant.now())
                .sign(algorithm);
        }
        catch (JWTCreationException e){
            throw new RuntimeException("Erro ao gerar token.", e);
        }
    }

    public String validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("fitsy-api")
                    .build()
                    .verify(token)
                    .getSubject();
        }
        catch (JWTVerificationException e){
            throw new RuntimeException("Token invalido.", e);
        }
    }
}
