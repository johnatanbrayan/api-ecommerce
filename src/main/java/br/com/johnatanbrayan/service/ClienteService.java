package br.com.johnatanbrayan.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.johnatanbrayan.domain.Cliente;
import br.com.johnatanbrayan.repository.ClienteRepository;
import br.com.johnatanbrayan.service.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente find(Long id) {
		Optional<Cliente> obj = clienteRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado: "+id+" ,Tipo: "+
		Cliente.class.getName()));
	}
}