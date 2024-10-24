package com.alura.forohub.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.alura.forohub.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String apiSecret;

    public String generarToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("Foro Hub")
                    .withSubject(usuario.getCorreoElectronico())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException();
        }
    }

    public String getSubject(String token) {
        if (token == null) {
            throw new RuntimeException();
        }
        DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret); // validando firma
            verifier = JWT.require(algorithm)
                    .withIssuer("Foro Hub")
                    .build()
                    .verify(token);
            verifier.getSubject();
        } catch (JWTVerificationException exception) {
            System.out.println(exception.toString());
        }
        if (verifier.getSubject() == null) {
            throw new RuntimeException("Verifier invalido");
        }
        return verifier.getSubject();
    }

    private Instant generarFechaExpiracion() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }

    public Long obtenerIdUsuario(String authHeader) {
        if (authHeader == null) {
            throw new RuntimeException("El token es nulo.");
        }
        String token = extractToken(authHeader);
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            DecodedJWT decodedJWT = JWT.require(algorithm)
                    .withIssuer("Foro Hub")
                    .build()
                    .verify(token);

            // Extraer el ID del claim "id"
            return decodedJWT.getClaim("id").asLong();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token inválido o manipulado: " + exception.getMessage());
        }
    }

    public String extractToken(String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.replace("Bearer ", "");
        }
        throw new IllegalArgumentException("Invalid Authorization header.");
    }

}
