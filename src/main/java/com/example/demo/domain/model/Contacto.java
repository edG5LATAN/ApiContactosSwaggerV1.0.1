package com.example.demo.domain.model;

import com.example.demo.domain.dto.DtoActualizar;
import com.example.demo.domain.dto.DtoContacto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "contacto")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String correo;
    private String telefono;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "contacto_id",referencedColumnName = "id")
    private List<Direccion> direcciones;



    public Contacto(DtoContacto dtoContacto) {
        this.nombre= dtoContacto.nombre();
        this.correo= dtoContacto.correo();
        this.telefono= dtoContacto.telefono();
        this.direcciones=dtoContacto.dtoDireccions().stream().map(Direccion::new).toList();
    }

    public void actualizar(DtoActualizar dtoActualizar) {
        if(dtoActualizar.nombre()!=""){
            this.nombre= dtoActualizar.nombre();
        }
        if(dtoActualizar.telefono()!=""){
            this.telefono= dtoActualizar.telefono();
        }
        if(dtoActualizar.correo()!=""){
            this.correo= dtoActualizar.correo();
        }
    }
}
