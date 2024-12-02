package br.com.microservice.stateless_any_api.core.service;


import br.com.microservice.stateless_any_api.core.dto.AuthUserResponse;
import br.com.microservice.stateless_any_api.infra.exception.AuthenticationException;
import br.com.microservice.stateless_any_api.infra.exception.ValidationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@RequiredArgsConstructor
public class JwtService {

    private static final Integer TOKEN_INDEX = 1;
    private static final String EMPTY_SPACE = " ";

    @Value("${app.token.secret-key}")
    private String secretKey;

    public AuthUserResponse getAutheticateUser(String token) {
        var tokenClaims = getClaims(token);

        var id = Integer.valueOf((String) tokenClaims.get("id"));
        var username = (String) tokenClaims.get("username");

        return new AuthUserResponse(id, username);

    }

    public boolean validateAccessToken(String token) {
        return getClaims(token) != null;
    }

    private Claims getClaims(String token) {
        var accessToken = extractToken(token);

        try {

            return Jwts
                    .parser()
                    .setSigningKey(generateSign())
                    .build()
                    .parseClaimsJws(accessToken)
                    .getBody();
        } catch (Exception ex) {
            throw new AuthenticationException("Invalid token " + ex.getMessage());
        }
    }

    private String extractToken(String token) {
        if (isEmpty(token))
            throw new ValidationException("The access token was not informed.");

        if (token.contains(EMPTY_SPACE))
            return token.split(EMPTY_SPACE)[TOKEN_INDEX];

        return token;
    }

    private SecretKey generateSign() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }
}
