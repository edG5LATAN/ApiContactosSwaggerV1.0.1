package com.example.demo.domain.dto;

import com.example.demo.domain.enumRole.EnumRole;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;

public record DtoUsuario(
        @NotNull String nombre,
        @NotNull String login,
        @NotNull String clave,
        @JsonAlias("rol")
        EnumRole role
) {
}
