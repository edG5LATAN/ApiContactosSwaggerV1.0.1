package com.example.demo.domain.dto;

import com.example.demo.domain.model.Contacto;
import com.example.demo.domain.model.Direccion;
import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public record DtoMostrarContacto(
        Long  id,
        String nombre,
        String correo,
        String telefono,
        @JsonAlias("direccion")
        List<DtoDireccion> direccions
) {
        public DtoMostrarContacto(Contacto contacto){
                this(contacto.getId(), contacto.getNombre(), contacto.getCorreo(),
                        contacto.getTelefono(),contacto.getDirecciones().stream().map(DtoDireccion::new).toList());
        }
}
