package br.com.microservice.stateless_auth_api.core.service;


import br.com.microservice.stateless_auth_api.core.model.User;
import br.com.microservice.stateless_auth_api.infra.exception.AuthenticationException;
import br.com.microservice.stateless_auth_api.infra.exception.ValidationException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@RequiredArgsConstructor
public class JWTService {

    private static final Integer ONE_DAY_IN_HOURS = 24;
    private static final Integer TOKEN_INDEX = 1;
    private static final String EMPTY_SPACE = " ";

    @Value("${app.token.secret-key}")
    private String secretKey;

    public String createToken(User user) {

        var data = new HashMap<String, String>();

        data.put("id", user.getId().toString());
        data.put("username", user.getUsername());

        return Jwts.builder().setClaims(data)
                .setExpiration(generateExpireAt())
                .signWith(generateSign())
                .compact();
    }


    public void validateAccessToken(String token) {
        var accessToken = extractToken(token);

        try {

            Jwts
                    .parser()
                    .setSigningKey(generateSign())
                    .build()
                    .parseClaimsJws(accessToken)
                    .getBody();
        } catch (Exception ex) {
            throw new AuthenticationException("Invalid token "+ ex.getMessage());
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

    private Date generateExpireAt() {
        return Date.from(
                LocalDateTime.now()
                        .plusHours(ONE_DAY_IN_HOURS)
                        .atZone(ZoneId.systemDefault())
                        .toInstant()
        );
    }
}
