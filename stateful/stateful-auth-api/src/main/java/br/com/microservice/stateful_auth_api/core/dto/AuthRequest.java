package br.com.microservice.stateful_auth_api.core.dto;

public record AuthRequest (String username, String password) {
}
