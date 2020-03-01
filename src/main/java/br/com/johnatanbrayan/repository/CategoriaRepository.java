package br.com.johnatanbrayan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.johnatanbrayan.domain.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria,Integer>{}
