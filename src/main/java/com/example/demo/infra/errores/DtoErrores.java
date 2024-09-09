package com.example.demo.infra.errores;

import org.springframework.validation.FieldError;

public record DtoErrores(
        String campo,
        String mensaje
) {
    public DtoErrores(FieldError error){
        this(error.getField(), error.getDefaultMessage());
    }
}
