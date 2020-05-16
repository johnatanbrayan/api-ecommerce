package br.com.johnatanbrayan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.johnatanbrayan.domain.Cidade;
import br.com.johnatanbrayan.domain.Cliente;
import br.com.johnatanbrayan.domain.Endereco;
import br.com.johnatanbrayan.domain.dto.ClienteDTO;
import br.com.johnatanbrayan.domain.dto.ClienteNewDTO;
import br.com.johnatanbrayan.domain.enums.TipoCliente;
import br.com.johnatanbrayan.repository.ClienteRepository;
import br.com.johnatanbrayan.repository.EnderecoRepository;
import br.com.johnatanbrayan.service.exception.DataIntegrityException;
import br.com.johnatanbrayan.service.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
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
	
	public Cliente insertCliente(Cliente cliente) {
		cliente.setId(null);
		cliente = clienteRepository.save(cliente);
		enderecoRepository.saveAll(cliente.getEnderecos());
		return cliente;
	}
	
	public Cliente updateCliente(Cliente cliente) {
		Cliente updateCliente = find(cliente.getId());
		updateData(updateCliente, cliente);
		return clienteRepository.save(updateCliente);
	}
	
	public void deleteCliente(Long id) {
		find(id);
		try {
			clienteRepository.deleteById(id);
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível exluir um cliente que possui relacionamento vinculados.");
		}
	}
	
	public Cliente fromDTO(ClienteDTO clienteDTO) {
		return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null, null);
	}
	
	public Cliente fromDTO(ClienteNewDTO clienteNewDTO) {
		Cliente cliente =  new Cliente(null, clienteNewDTO.getNome(), clienteNewDTO.getEmail(), clienteNewDTO.getCpfOuCnpj(), TipoCliente.toEnum(clienteNewDTO.getTipoCliente()));
		Cidade cidade = new Cidade(clienteNewDTO.getCidadeId(), null, null);
		Endereco endereco1 = new Endereco(null, clienteNewDTO.getLogradouro(), clienteNewDTO.getNumero(), clienteNewDTO.getBairro(), clienteNewDTO.getComplemento(), clienteNewDTO.getCep(), cidade, cliente); 
		cliente.getEnderecos().add(endereco1);
		cliente.getTelefones().add(clienteNewDTO.getTelefone1());
		if(clienteNewDTO.getTelefone2() != null) {
			cliente.getTelefones().add(clienteNewDTO.getTelefone2());
			
		}
		if(clienteNewDTO.getTelefone2() != null) {
			cliente.getTelefones().add(clienteNewDTO.getTelefone3());
		}
		
		return cliente;
	}
	
	public void updateData(Cliente updateCliente, Cliente cliente ) {
		updateCliente.setNome(cliente.getNome());
		updateCliente.setEmail(cliente.getEmail());
	}
}