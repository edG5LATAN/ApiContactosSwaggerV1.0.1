package com.example.demo.infra.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class AuthenticationConfiguracion {

    private final FiltroDeUsuario filtroDeUsuario;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(crs->crs.disable())
                .sessionManagement(sess->sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(autr->autr.requestMatchers("/usuario/login","/v3/api-docs/**",
                                        "/swagger-ui/**")
//    ,"/swagger-ui/**", "/v3/api-docs/**" ,"/swagger-resources/**", "/webjars/**")
                        .permitAll()
                        .requestMatchers("/contacto/borrar/**","/direccion/borrar/**"
                        )
                        .hasRole("ADMIN")
                        .requestMatchers("/contacto/actualizar/**","/direccion/actualizar/**"
                        )
                        .hasAnyRole("ADMIN","USER")
                        .anyRequest()
                        .authenticated()
                )
                .addFilterBefore(filtroDeUsuario, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
