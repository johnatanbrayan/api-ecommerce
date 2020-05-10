package br.com.johnatanbrayan.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.johnatanbrayan.domain.Pedido;
import br.com.johnatanbrayan.repository.PedidoRepository;
import br.com.johnatanbrayan.service.exception.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido find(Long id) {
		Optional<Pedido> obj = pedidoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Pedido não encontrado. Id: " + id + ",tipo: " + Pedido.class.getName()));
	}
}
