package br.com.johnatanbrayan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.johnatanbrayan.domain.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido,Long>{}
