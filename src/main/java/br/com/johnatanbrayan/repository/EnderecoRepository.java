package br.com.johnatanbrayan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.johnatanbrayan.domain.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco,Long>{}
