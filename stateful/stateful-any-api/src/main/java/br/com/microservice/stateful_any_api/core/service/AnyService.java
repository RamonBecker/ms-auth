package br.com.microservice.stateful_any_api.core.service;

import br.com.microservice.stateful_any_api.core.dto.AnyResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AnyService {

    private final TokenService service;

    public AnyResponse getData(String accessToken) {
        service.validateToken(accessToken);
        var authUser = service.getAutheticatedUser(accessToken);
        var ok = HttpStatus.OK;
        return new AnyResponse(ok.name(), ok.value(), authUser);
    }
}
