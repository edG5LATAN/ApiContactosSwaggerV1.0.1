package com.example.demo.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.domain.model.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.secret}")
    private String apisecret;

    public String getToken(Usuario usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(apisecret);
            return JWT.create()
                    .withIssuer("agenda")
                    .withSubject(usuario.getLogin())
                    .withClaim("id",usuario.getId())
                    .withExpiresAt(horaExpira())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException(exception);
        }
    }

    public String getSubject(String token){
        DecodedJWT verifier=null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(apisecret);
            verifier = JWT.require(algorithm)
                    .withIssuer("agenda")
                    .build()
                    .verify(token);

        } catch (JWTVerificationException exception){
            System.out.println(exception.toString());
        }
        if(verifier.getSubject()==null){
            throw new RuntimeException();
        }
        return verifier.getSubject();
    }

    public Instant horaExpira(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-06:00"));
    }
}
