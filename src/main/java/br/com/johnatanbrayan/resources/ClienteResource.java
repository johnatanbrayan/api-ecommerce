package br.com.johnatanbrayan.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.johnatanbrayan.domain.Cliente;
import br.com.johnatanbrayan.domain.dto.ClienteDTO;
import br.com.johnatanbrayan.repository.ClienteRepository;
import br.com.johnatanbrayan.service.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Cliente> get(@PathVariable Long id) {
		Cliente cliente = clienteService.find(id);
		return ResponseEntity.ok().body(cliente);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> findAllCliente() {
		List<Cliente> clientes = clienteService.findAllCliente();
		List<ClienteDTO> clientesDTO = clientes.stream().map(x -> new ClienteDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(clientesDTO);
	}
	
	@RequestMapping(value="page", method=RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> findPageCliente(@RequestParam(value="page", defaultValue="0") Integer page, @RequestParam(value="linePerPage", defaultValue="24") Integer linePerPage, @RequestParam(value="direction", defaultValue="ASC") String direction, @RequestParam(value="orderBy", defaultValue="nome") String orderBy) {
		Page<Cliente> pageClientes = clienteService.findPageCliente(page, linePerPage, direction, orderBy);
		Page<ClienteDTO> pageClientesDTO = pageClientes.map(x -> new ClienteDTO(x));
		return ResponseEntity.ok().body(pageClientesDTO);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> updateCliente(@RequestBody ClienteDTO clienteDTO, @PathVariable Long id) {
		Cliente cliente = clienteService.fromDTO(clienteDTO);
		cliente.setId(id);
		cliente = clienteService.updateCliente(cliente);
		return ResponseEntity.noContent().build();
	}
}
