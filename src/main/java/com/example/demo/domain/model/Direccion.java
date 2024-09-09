package com.example.demo.domain.model;


import com.example.demo.domain.dto.DtoDireccion;
import com.example.demo.domain.dto.DtoDireccionCrear;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "direccion")
public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ciudad;
    private String colonia;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    private Contacto contacto;

    public Direccion(DtoDireccion dtoDireccion) {
        this.ciudad= dtoDireccion.ciudad();
        this.colonia= dtoDireccion.colonia();
    }

    public Direccion(DtoDireccionCrear dtoDireccionCrear, Contacto contacto) {
        this.ciudad= dtoDireccionCrear.ciudad();
        this.colonia=dtoDireccionCrear.colonia();
        this.contacto=contacto;
    }

    public void actualizar(DtoDireccion dtoDireccion) {
        if(dtoDireccion.ciudad()!=null){
            this.ciudad=dtoDireccion.ciudad();
        }
        if(dtoDireccion.colonia()!=null){
            this.colonia= dtoDireccion.colonia();
        }
    }
}
