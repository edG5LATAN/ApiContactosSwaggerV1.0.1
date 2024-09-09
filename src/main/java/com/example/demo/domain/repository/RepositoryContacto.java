package com.example.demo.domain.repository;

import com.example.demo.domain.model.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryContacto extends JpaRepository<Contacto,Long> {
    Contacto findByNombre(String contacto);
}
