package com.example.demo.infra.errores;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorErrores {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity datoNulo(MethodArgumentNotValidException errores){
       var error=errores.getFieldErrors().stream().map(DtoErrores::new).toList();
        return ResponseEntity.ok(error);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity noEncontrado(){
        return ResponseEntity.notFound().build();
    }
}
