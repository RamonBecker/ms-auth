package br.com.microservice.stateless_auth_api.core.service;


import br.com.microservice.stateless_auth_api.core.dto.AuthRequest;
import br.com.microservice.stateless_auth_api.core.dto.TokenDTO;
import br.com.microservice.stateless_auth_api.core.repository.IUserRepository;
import br.com.microservice.stateless_auth_api.infra.exception.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@AllArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final IUserRepository userRepository;

    public TokenDTO login(AuthRequest request) {

        var user = userRepository
                .findByUsername(request.username())
                .orElseThrow(() -> new ValidationException("Incorrect username or password!"));


        var accessToken = jwtService.createToken(user);

        if (!validatePassword(request.password(), user.getPassword()))
            throw new ValidationException("Unable to login!");

        return new TokenDTO(accessToken);
    }

    private boolean validatePassword(String rawPassword, String encoderPassword) {

        if (isEmpty(rawPassword))
            throw new ValidationException("Incorrect username or password!");

        if (!passwordEncoder.matches(rawPassword, encoderPassword))
            throw new ValidationException("Incorrect username or password!");

        return true;
    }

    public TokenDTO validateToken(String accessToken) {

        if (validateExistingToken(accessToken)) {
            jwtService.validateAccessToken(accessToken);
            return new TokenDTO(accessToken);
        }

        throw new ValidationException("Access token invalid!");
    }

    private boolean validateExistingToken(String accessToken) {
        if (isEmpty(accessToken))
            throw new ValidationException("The access token must be informed!");

        return true;
    }

}
