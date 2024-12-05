package br.com.microservice.stateful_auth_api.core.service;

import br.com.microservice.stateful_auth_api.core.dto.TokenData;
import br.com.microservice.stateful_auth_api.infra.exception.AuthenticationException;
import br.com.microservice.stateful_auth_api.infra.exception.ValidationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@AllArgsConstructor
public class TokenService {

    private static final Long ONE_DAY_IN_SECONDS = 86400L;
    private static final Integer TOKEN_INDEX = 1;
    private static final String EMPTY_SPACE = " ";

    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;

    public String createToken(String username) {
        var accessToken = UUID.randomUUID().toString();
        var data = new TokenData(username);
        var jsonData = getJsonData(data);

        if (isEmpty(jsonData))
            throw new AuthenticationException("Error loading data!");

        redisTemplate.opsForValue().set(accessToken, jsonData);
        redisTemplate.expireAt(accessToken, Instant.now().plusSeconds(ONE_DAY_IN_SECONDS));

        return accessToken;
    }

    public TokenData getTokenData(String token) {
        var accessToken = extractToken(token);
        var json = getRedisTokenValue(accessToken);

        try {
            return objectMapper.readValue(json, TokenData.class);
        } catch (Exception e) {
            throw new AuthenticationException("Error extracing the authenticated user! " + e.getMessage());
        }
    }

    public void deleteRedisToken(String token) {
        var accessToken = extractToken(token);
        redisTemplate.delete(accessToken);
    }

    public boolean validateAccessToken(String token) {
        var accessToken = extractToken(token);
        var data = getRedisTokenValue(accessToken);
        return !isEmpty(data);
    }

    private String getRedisTokenValue(String token) {
        return redisTemplate.opsForValue().get(token);
    }

    private String getJsonData(Object payload) {
        try {
            return objectMapper.writeValueAsString(payload);
        } catch (Exception e) {
            return EMPTY_SPACE;
        }
    }

    private String extractToken(String token) {
        if (isEmpty(token))
            throw new ValidationException("The access token was not informed.");

        if (token.contains(EMPTY_SPACE))
            return token.split(EMPTY_SPACE)[TOKEN_INDEX];

        return token;
    }
}
