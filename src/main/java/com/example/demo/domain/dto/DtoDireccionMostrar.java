package com.example.demo.domain.dto;

import com.example.demo.domain.model.Direccion;

public record DtoDireccionMostrar(
        Long id,
        String ciudad,
        String colonia,
        String contacto
) {
    public DtoDireccionMostrar(Direccion direccion){
        this(direccion.getId(), direccion.getCiudad(), direccion.getColonia(), direccion.getContacto().getNombre());
    }
}
