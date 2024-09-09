package com.example.demo.infra.security;


import com.example.demo.domain.repository.RepositoryUsuario;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class FiltroDeUsuario extends OncePerRequestFilter {


    private final TokenService tokenService;
    private final RepositoryUsuario repositoryUsuario;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var autToken= request.getHeader("Authorization");
        if(autToken!=null){
            var token = autToken.replace("Bearer ","");
            var subject= tokenService.getSubject(token);
            if(subject!=null){
                var usuario= repositoryUsuario.findByLogin(subject);
                var autenticacion=new UsernamePasswordAuthenticationToken(usuario,null,usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(autenticacion);

            }
        }
        filterChain.doFilter(request,response);

    }
}
