package com.example.demo.controller;

import com.example.demo.domain.dto.DtoActualizar;
import com.example.demo.domain.dto.DtoContacto;
import com.example.demo.domain.dto.DtoDireccion;
import com.example.demo.domain.dto.DtoMostrarContacto;
import com.example.demo.domain.model.Contacto;
import com.example.demo.domain.model.Direccion;
import com.example.demo.domain.repository.RepositoryContacto;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("contacto")
@RequiredArgsConstructor
@Transactional
@Tag(
        name = "Controlador de contacto",
        description = "controlador para la creacion, edicion, lectura y eliminacion contactos"
)
public class ControllerContacto {

    private final RepositoryContacto repositoryContacto;

    @PostMapping("crear")
    public ResponseEntity crear(@RequestBody @Valid DtoContacto dtoContacto, UriComponentsBuilder uriComponentsBuilder){
        var newA=dtoContacto.dtoDireccions().stream().map(Direccion::new).toList();
        var contacto=repositoryContacto.save(new Contacto(dtoContacto));
        URI url= uriComponentsBuilder.buildAndExpand(contacto.getId()).toUri();
       return ResponseEntity.created(url).body((new DtoMostrarContacto(contacto.getId(), contacto.getNombre(), contacto.getTelefono(),
               contacto.getCorreo(), contacto.getDirecciones().stream().map(DtoDireccion::new).toList())));
    }

    @GetMapping("mostrar")
    public ResponseEntity<Page<DtoMostrarContacto>> mostrar(@PageableDefault(size = 4) Pageable pageable){
        return ResponseEntity.ok(repositoryContacto.findAll(pageable).map(DtoMostrarContacto::new));
    }

    @DeleteMapping("borrar/{id}")
    public ResponseEntity eliminar(@PathVariable Long id){
        var contacto=repositoryContacto.getReferenceById(id);
        repositoryContacto.delete(contacto);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("actualizar/{id}")
    public ResponseEntity actualizar(@PathVariable Long id,@RequestBody DtoActualizar dtoActualizar){
        var contacto=repositoryContacto.getReferenceById(id);
        contacto.actualizar(dtoActualizar);
        return ResponseEntity.ok(new DtoMostrarContacto(contacto.getId(), contacto.getNombre(), contacto.getTelefono(),
                contacto.getCorreo(), contacto.getDirecciones().stream().map(DtoDireccion::new).toList()));
    }

    @GetMapping("unidad/{id}")
    public ResponseEntity unidad(@PathVariable Long id){
        var contacto= repositoryContacto.getReferenceById(id);
        return ResponseEntity.ok(new DtoMostrarContacto(contacto.getId(), contacto.getNombre(), contacto.getTelefono(),
                contacto.getCorreo(), contacto.getDirecciones().stream().map(DtoDireccion::new).toList()));
    }

}
