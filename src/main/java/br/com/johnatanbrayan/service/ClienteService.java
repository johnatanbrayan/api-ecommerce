package br.com.johnatanbrayan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.johnatanbrayan.domain.Cliente;
import br.com.johnatanbrayan.repository.ClienteRepository;
import br.com.johnatanbrayan.service.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente find(Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado: "+id+" ,Tipo: "+
		Cliente.class.getName()));
	}
	
	public List<Cliente> findAllCliente() {
		return clienteRepository.findAll();
	}
	
	public Page<Cliente> findPageCliente(Integer page, Integer linePerPage, String direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linePerPage, Direction.valueOf(direction), orderBy);
		Page<Cliente> pageClientes = clienteRepository.findAll(pageRequest);
		return pageClientes;
	}
}