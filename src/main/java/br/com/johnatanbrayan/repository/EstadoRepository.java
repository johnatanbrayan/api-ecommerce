package br.com.johnatanbrayan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.johnatanbrayan.domain.Estado;

public interface EstadoRepository extends JpaRepository<Estado,Long>{}