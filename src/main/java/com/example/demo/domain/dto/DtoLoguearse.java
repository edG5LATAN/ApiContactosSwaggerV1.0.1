package com.example.demo.domain.dto;

import jakarta.validation.constraints.NotNull;

public record DtoLoguearse(
       @NotNull String login,
       @NotNull String  clave
) {
}
