package br.com.johnatanbrayan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.johnatanbrayan.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto,Long>{}
