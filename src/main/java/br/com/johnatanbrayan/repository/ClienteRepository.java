package br.com.johnatanbrayan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.johnatanbrayan.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{}
