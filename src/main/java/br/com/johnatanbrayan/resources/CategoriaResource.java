package br.com.johnatanbrayan.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.johnatanbrayan.domain.Categoria;
import br.com.johnatanbrayan.domain.dto.CategoriaDTO;
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
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> findAllCategoria() {
		List<Categoria> categorias = categoriaService.findAllCategoria();
		List<CategoriaDTO> categoriasDTO = categorias.stream().map(categoria -> new CategoriaDTO(categoria)).collect(Collectors.toList());
		return ResponseEntity.ok().body(categoriasDTO);
	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<CategoriaDTO>> findPageCategoria(@RequestParam(value= "page", defaultValue="0") Integer page, @RequestParam(value="linePerPage", defaultValue="24") Integer linePerPage, @RequestParam(value="direction", defaultValue="ASC") String direction, @RequestParam(value="orderBy", defaultValue="nome") String orderBy) {
		Page<Categoria> pageCategorias = categoriaService.findPageCategoria(page, linePerPage, direction, orderBy);
		Page<CategoriaDTO> pageCategoriasDTO = pageCategorias.map(categoria -> new CategoriaDTO(categoria));
		return ResponseEntity.ok().body(pageCategoriasDTO);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Categoria> getCategoria(@PathVariable Long id) {
		Categoria categoria = categoriaService.find(id);
		return ResponseEntity.ok().body(categoria);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insertCategoria(@Valid @RequestBody CategoriaDTO categoriaDTO) {
		Categoria categoria = categoriaService.fromDTO(categoriaDTO);
		categoria = categoriaService.insertCategoria(categoria);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> updateCategoria(@Valid @RequestBody CategoriaDTO categoriaDTO, @PathVariable Long id) {
		Categoria categoria = categoriaService.fromDTO(categoriaDTO);
		categoria.setId(id);
		categoria = categoriaService.updateCategoria(categoria);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteCategoria(@PathVariable Long id) {
		categoriaService.deleteCategoria(id);
		return ResponseEntity.noContent().build();
	}
}