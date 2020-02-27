package br.com.johnatanbrayan.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.johnatanbrayan.domain.Categoria;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Categoria> GetCategoria(){
		Categoria cat1 = new Categoria(1,"Esportista");
		Categoria cat2 = new Categoria(2,"Mercado");
		
		List<Categoria> listCat = new ArrayList<>();
		
		listCat.add(cat1);
		listCat.add(cat2);
		return listCat;
	}
}