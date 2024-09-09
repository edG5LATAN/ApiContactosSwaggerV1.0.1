package com.example.demo.domain.repository;

import com.example.demo.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface RepositoryUsuario extends JpaRepository<Usuario,Long> {
    UserDetails findByLogin(String username);
}
