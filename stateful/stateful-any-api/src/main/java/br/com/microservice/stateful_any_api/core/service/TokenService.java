package br.com.microservice.stateful_any_api.core.service;

import br.com.microservice.stateful_any_api.core.client.TokenClient;
import br.com.microservice.stateful_any_api.core.dto.AuthUserResponse;
import br.com.microservice.stateful_any_api.infra.exception.AuthenticationException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class TokenService {

    private final TokenClient client;

    public void validateToken(String token) {

        try {

            log.info("Sending request for token validation: {}", token);

            var response = client.validateToken(token);

            log.info("Token is valid: {}", response.accessToken());


        } catch (Exception e) {
            throw new AuthenticationException("Auth error: " + e.getMessage());
        }
    }

    public AuthUserResponse getAutheticatedUser(String token) {
        try {
            log.info("Sending request for auth user: {}", token);
            var response = client.getAutheticatedUser(token);


            log.info("Auth user found: {} and token: {}", response.toString(), token);



            return response;

        } catch (Exception e) {
            throw new AuthenticationException("Auth to get authenticated user: " + e.getMessage());

        }
    }
}