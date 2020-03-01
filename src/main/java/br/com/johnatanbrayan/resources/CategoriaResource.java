package br.com.johnatanbrayan.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.johnatanbrayan.domain.Categoria;
import br.com.johnatanbrayan.service.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	// @Autowired
	// private CategoriaRepository categoriaRepository;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> get(@PathVariable Integer id){
		Categoria obj = categoriaService.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	/*
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> getCategoriaId(@PathVariable Integer id){
		Optional<Categoria> obj = categoriaRepository.findById(id);
		return ResponseEntity.ok().body(obj.orElse(null));
	}
	*/
}