package br.com.microservice.stateful_auth_api.core.repository;

import br.com.microservice.stateful_auth_api.core.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
}
