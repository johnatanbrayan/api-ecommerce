package br.com.johnatanbrayan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.johnatanbrayan.domain.Categoria;
import br.com.johnatanbrayan.repository.CategoriaRepository;
import br.com.johnatanbrayan.service.exception.DataIntegrityException;
import br.com.johnatanbrayan.service.exception.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria find(Long id) {
		Optional<Categoria> obj = categoriaRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: "+id+" ,Tipo: "+Categoria.class.getName()));
	}
	
	public Categoria insertCategoria(Categoria obj) {
		obj.setId(null);
		return categoriaRepository.save(obj);
	}
	
	public Categoria updateCategoria(Categoria obj) {
		return categoriaRepository.save(obj);
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
}