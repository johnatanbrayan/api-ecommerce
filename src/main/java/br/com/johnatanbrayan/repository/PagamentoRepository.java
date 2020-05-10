package br.com.johnatanbrayan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.johnatanbrayan.domain.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento,Long>{}
