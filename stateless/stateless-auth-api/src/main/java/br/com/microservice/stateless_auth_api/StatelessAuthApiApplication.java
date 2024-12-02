package br.com.microservice.stateless_auth_api;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log4j2
public class StatelessAuthApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(StatelessAuthApiApplication.class, args);
    }

}
