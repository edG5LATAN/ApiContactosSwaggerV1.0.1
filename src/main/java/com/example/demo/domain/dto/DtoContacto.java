package com.example.demo.domain.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DtoContacto(
        @NotNull String nombre,
        @NotNull String correo,
        @NotNull String telefono,
        @JsonAlias("direccion")
        List<DtoDireccion> dtoDireccions
) {
}
