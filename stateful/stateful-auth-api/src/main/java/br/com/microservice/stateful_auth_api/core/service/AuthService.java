package br.com.microservice.stateful_auth_api.core.service;


import br.com.microservice.stateful_auth_api.core.dto.AuthRequest;
import br.com.microservice.stateful_auth_api.core.dto.AuthUserResponse;
import br.com.microservice.stateful_auth_api.core.dto.TokenDTO;
import br.com.microservice.stateful_auth_api.core.model.User;
import br.com.microservice.stateful_auth_api.core.repository.IUserRepository;
import br.com.microservice.stateful_auth_api.infra.exception.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@AllArgsConstructor
public class AuthService {
    private final IUserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;


    public TokenDTO login(AuthRequest request) {
        var user = findByUsername(request.username());
        var accessToken = tokenService.createToken(user.getUsername());

        if (!validatePassword(request.password(), user.getPassword()))
            throw new ValidationException("Unable to login!");

        return new TokenDTO(accessToken);
    }

    public TokenDTO validateToken(String accessToken) {
        if (!validateExistingToken(accessToken))
            throw new ValidationException("Token invalid!");
        if(!tokenService.validateAccessToken(accessToken))
            throw new ValidationException("Token invalid!");
        return new TokenDTO(accessToken);
    }

    public void logout(String accessToken){
        tokenService.deleteRedisToken(accessToken);
    }

    public AuthUserResponse getAuthenticatedUser(String accessToken){
        var tokenData = tokenService.getTokenData(accessToken);
        var user = findByUsername(tokenData.username());
        return new AuthUserResponse(user.getId(), user.getUsername());
    }

    private User findByUsername(String username) {
        return repository
                .findByUsername(username)
                .orElseThrow(() -> new ValidationException("User not found!"));
    }

    private boolean validateExistingToken(String accessToken) {
        if (isEmpty(accessToken))
            throw new ValidationException("The access token must be informed!");

        return true;
    }

    private boolean validatePassword(String rawPassword, String encoderPassword) {

        if (isEmpty(rawPassword))
            throw new ValidationException("Incorrect username or password!");

        if (!passwordEncoder.matches(rawPassword, encoderPassword))
            throw new ValidationException("Incorrect username or password!");

        return true;
    }

}
