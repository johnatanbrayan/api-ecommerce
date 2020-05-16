package br.com.johnatanbrayan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.johnatanbrayan.domain.Categoria;
import br.com.johnatanbrayan.domain.dto.CategoriaDTO;
import br.com.johnatanbrayan.repository.CategoriaRepository;
import br.com.johnatanbrayan.service.exception.DataIntegrityException;
import br.com.johnatanbrayan.service.exception.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria find(Long id) {
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		return categoria.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: "+id+" ,Tipo: "+Categoria.class.getName()));
	}
	
	public Categoria insertCategoria(Categoria categoria) {
		categoria.setId(null);
		return categoriaRepository.save(categoria);
	}
	
	public Categoria updateCategoria(Categoria categoria) {
		Categoria updateCategoria = find(categoria.getId());
		updateData(updateCategoria, categoria);
		return categoriaRepository.save(updateCategoria);
	}
	
	public void deleteCategoria(Long id) {
		find(id);
		
		try {
			categoriaRepository.deleteById(id);
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos!!");
		}
	}
	
	public List<Categoria> findAllCategoria() {
		return categoriaRepository.findAll();
	}
	
	public Page<Categoria> findPageCategoria(Integer page, Integer linePerPage, String direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linePerPage, Direction.valueOf(direction), orderBy);
		return categoriaRepository.findAll(pageRequest);
	}
	
	public Categoria fromDTO(CategoriaDTO categoriaDTO) {
		return new Categoria(categoriaDTO.getId(), categoriaDTO.getNome());
	}
	
	public void updateData(Categoria updateCategoria, Categoria categoria) {
		updateCategoria.setNome(categoria.getNome());
	}
}