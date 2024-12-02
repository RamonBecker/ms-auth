package br.com.microservice.stateless_any_api.core.service;

import br.com.microservice.stateless_any_api.core.dto.AnyResponse;
import br.com.microservice.stateless_any_api.infra.exception.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class AnyService {

    private final JwtService jwtService;


    public AnyResponse getData(String accessToken) {
        var validate = jwtService.validateAccessToken(accessToken);

        if (!validate)
            throw new ValidationException("Token invalid!");

        var authUser = jwtService.getAutheticateUser(accessToken);
        var ok = HttpStatus.OK;
        return new AnyResponse(ok.name(), ok.value(), authUser);

    }
}
