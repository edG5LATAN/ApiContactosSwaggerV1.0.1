package com.example.demo.controller;

import com.example.demo.domain.dto.DtoLoguearse;
import com.example.demo.domain.model.Usuario;
import com.example.demo.infra.security.DtoToken;
import com.example.demo.infra.security.TokenService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("usuario")
@RequiredArgsConstructor
@Tag(
        name = "Controlador Para Login",
        description = "controlador para la creacion de token y su rol de usuario"
)
public class ControllerUsuario {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping("login")
    public ResponseEntity loguearse(@RequestBody @Valid DtoLoguearse dtoLoguearse){

        Authentication autoken= new UsernamePasswordAuthenticationToken(dtoLoguearse.login(),dtoLoguearse.clave());
        var usuario= authenticationManager.authenticate(autoken);
        var token= tokenService.getToken((Usuario) usuario.getPrincipal());
        return ResponseEntity.ok(new DtoToken(token));

    }

}
