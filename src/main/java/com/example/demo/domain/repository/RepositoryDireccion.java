package com.example.demo.domain.repository;

import com.example.demo.domain.model.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryDireccion extends JpaRepository<Direccion,Long> {
}
