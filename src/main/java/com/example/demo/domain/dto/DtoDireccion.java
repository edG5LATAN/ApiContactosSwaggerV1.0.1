package com.example.demo.domain.dto;

import com.example.demo.domain.model.Direccion;
import jakarta.validation.constraints.NotNull;

public record DtoDireccion(
        @NotNull String ciudad,
        @NotNull String colonia
        ) {

        public DtoDireccion(Direccion direccion){
             this(direccion.getCiudad(), direccion.getColonia());
        }
}
