package br.com.johnatanbrayan;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.johnatanbrayan.domain.Categoria;
import br.com.johnatanbrayan.domain.Produto;
import br.com.johnatanbrayan.repository.CategoriaRepository;
import br.com.johnatanbrayan.repository.ProdutoRepository;

@SpringBootApplication
public class EcommerceApplication implements CommandLineRunner{

	public static void main(String[] args){
		SpringApplication.run(EcommerceApplication.class, args);
	}
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Autowired
	ProdutoRepository produtoRepository;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Categoria cat1 = new Categoria(null,"Escritório");
		Categoria cat2 = new Categoria(null,"Informática");
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));	
		
		Produto prod1 = new Produto(null,"Computador",2000.00);
		Produto prod2 = new Produto(null,"Impressora",800.00);
		Produto prod3 = new Produto(null,"Mouse",80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(prod1,prod2,prod3));
		cat2.getProdutos().addAll(Arrays.asList(prod2));
		
		prod1.getCategorias().addAll(Arrays.asList(cat1));
		prod2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		prod3.getCategorias().addAll(Arrays.asList(cat1));
		
		produtoRepository.saveAll(Arrays.asList(prod1,prod2,prod3));
	}
}