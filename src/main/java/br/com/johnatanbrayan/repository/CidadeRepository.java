package br.com.johnatanbrayan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.johnatanbrayan.domain.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade,Long>{}
