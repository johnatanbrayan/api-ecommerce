package br.com.johnatanbrayan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.johnatanbrayan.domain.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long>{}
