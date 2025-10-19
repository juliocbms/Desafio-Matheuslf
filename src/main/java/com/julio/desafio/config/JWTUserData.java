package com.julio.desafio.config;

import lombok.Builder;

@Builder
public record JWTUserData(Long userId, String email) {
}
