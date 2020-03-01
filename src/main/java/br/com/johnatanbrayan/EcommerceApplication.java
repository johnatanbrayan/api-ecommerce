package br.com.johnatanbrayan;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.johnatanbrayan.domain.Categoria;
import br.com.johnatanbrayan.repository.CategoriaRepository;

@SpringBootApplication
public class EcommerceApplication implements CommandLineRunner{

	public static void main(String[] args){
		SpringApplication.run(EcommerceApplication.class, args);
	}
	
	@Autowired
	CategoriaRepository categoriaRepository;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Categoria cat1 = new Categoria(null,"Escritório");
		Categoria cat2 = new Categoria(null,"Remédio");
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));	
	}
}
