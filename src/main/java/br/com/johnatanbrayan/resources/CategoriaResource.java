package br.com.johnatanbrayan.resources;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.johnatanbrayan.domain.Categoria;
import br.com.johnatanbrayan.service.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	// @Autowired
	// private CategoriaRepository categoriaRepository;
	
	@Autowired
	private CategoriaService categoriaService;
	
	/*
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> getCategoriaId(@PathVariable Integer id) {
		Optional<Categoria> obj = categoriaRepository.findById(id);
		return ResponseEntity.ok().body(obj.orElse(null));
	}
	*/
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Categoria> get(@PathVariable Long id) {
		Categoria obj = categoriaService.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insertCategoria(@RequestBody Categoria obj) throws URISyntaxException {
		obj = categoriaService.insertCategoria(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> updateCategoria(@RequestBody Categoria obj, @PathVariable Long id) {
		obj.setId(id);
		obj = categoriaService.updateCategoria(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteCategoria(@PathVariable Long id) {
		categoriaService.deleteCategoria(id);
		return ResponseEntity.noContent().build();
	}
}