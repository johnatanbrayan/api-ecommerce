package br.com.johnatanbrayan;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.johnatanbrayan.domain.Categoria;
import br.com.johnatanbrayan.domain.Cidade;
import br.com.johnatanbrayan.domain.Endereco;
import br.com.johnatanbrayan.domain.Estado;
import br.com.johnatanbrayan.domain.Produto;
import br.com.johnatanbrayan.repository.CategoriaRepository;
import br.com.johnatanbrayan.repository.CidadeRepository;
import br.com.johnatanbrayan.repository.EnderecoRepository;
import br.com.johnatanbrayan.repository.EstadoRepository;
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
	
	@Autowired
	CidadeRepository cidadeRepository;
	
	@Autowired
	EstadoRepository estadoRepository;
	
	@Autowired
	EnderecoRepository enderecoRepository;

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
		
		Estado e1 = new Estado(null,"Minas Gerais");
		Estado e2 = new Estado(null,"São Paulo");
		
		Cidade c1 = new Cidade(null,"Urbelândia",e1);
		Cidade c2 = new Cidade(null,"São Paulo",e2);
		Cidade c3 = new Cidade(null,"Campinas",e2);	
		
		//e1.setCidade(Arrays.asList(c1,c2,c3));
		
		e1.getCidade().addAll(Arrays.asList(c1,c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(e1,e2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Endereco end1 = new Endereco(null, "Rua Flores", "300", "Jardim", "Apto 203", "38220834", c1);
		Endereco end2 = new Endereco(null, "Avenida Matos", "105", "Centro", "Sala 800", "38777012", c2);
		
		c1.getEndereco().addAll(Arrays.asList(end1));
		c2.getEndereco().addAll(Arrays.asList(end2));
		
		enderecoRepository.saveAll(Arrays.asList(end1,end2));
	}
}