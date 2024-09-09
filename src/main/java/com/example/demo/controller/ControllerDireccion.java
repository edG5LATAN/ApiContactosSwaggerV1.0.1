package com.example.demo.controller;

import com.example.demo.domain.dto.DtoDireccion;
import com.example.demo.domain.dto.DtoDireccionCrear;
import com.example.demo.domain.dto.DtoDireccionMostrar;
import com.example.demo.domain.model.Direccion;
import com.example.demo.domain.repository.RepositoryContacto;
import com.example.demo.domain.repository.RepositoryDireccion;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("direccion")
@Transactional
@RequiredArgsConstructor
@Tag(
        name = "Controlador de direccion",
        description = "controlador para la creacion, edicion, lectura y eliminacion direcciones"
)
public class ControllerDireccion {

    private final RepositoryDireccion repositoryDireccion;
    private final RepositoryContacto repositoryContacto;

    @GetMapping("mostrar")
    public ResponseEntity<List<DtoDireccionMostrar>> mostrar(){
        return ResponseEntity.ok(repositoryDireccion.findAll().stream().map(DtoDireccionMostrar::new).toList());
    }

    @PostMapping("crear")
    public ResponseEntity crear(@RequestBody @Valid DtoDireccionCrear dtoDireccionCrear, UriComponentsBuilder uriComponentsBuilder){
        var contacto= repositoryContacto.findByNombre(dtoDireccionCrear.contacto());
        if(contacto!=null){
            var direccion=repositoryDireccion.save(new Direccion(dtoDireccionCrear,contacto));
            URI url=uriComponentsBuilder.path("direccion/unidad/{id}").buildAndExpand(direccion.getId()).toUri();
            return ResponseEntity.created(url).body(new DtoDireccionMostrar(direccion.getId(),
                    direccion.getCiudad(), direccion.getColonia(),
                    direccion.getContacto().getNombre()));
        }else {
            return ResponseEntity.ok("agregar un nombre de contacto que exista");
        }
    }

    @DeleteMapping("borrar/{id}")
    public ResponseEntity borrar(@PathVariable Long id){
        var direccion = repositoryDireccion.getReferenceById(id);
        if(direccion!=null){
            repositoryDireccion.delete(direccion);
            return ResponseEntity.ok("borrado  correctamente");
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("actualizar/{id}")
    public ResponseEntity actualizar(@PathVariable Long id,@RequestBody DtoDireccion dtoDireccion){
        var direccion = repositoryDireccion.getReferenceById(id);
        if(direccion!=null){
            direccion.actualizar(dtoDireccion);
            return ResponseEntity.ok(new DtoDireccionMostrar(direccion.getId(),
                    direccion.getCiudad(), direccion.getColonia(),
                    direccion.getContacto().getNombre()));
        }else {
            return ResponseEntity.ok("no se encontro el iddedireccion");
        }
    }

    @GetMapping("unidad/{id}")
    public ResponseEntity unidad(@PathVariable Long id){
        var direccion =repositoryDireccion.getReferenceById(id);
        return ResponseEntity.ok(new DtoDireccionMostrar(direccion.getId(),
                direccion.getCiudad(), direccion.getColonia(),
                direccion.getContacto().getNombre()));
    }



}
