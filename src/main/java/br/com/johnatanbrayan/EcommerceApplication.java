package br.com.johnatanbrayan;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.johnatanbrayan.domain.Categoria;
import br.com.johnatanbrayan.domain.Cidade;
import br.com.johnatanbrayan.domain.Cliente;
import br.com.johnatanbrayan.domain.Endereco;
import br.com.johnatanbrayan.domain.Estado;
import br.com.johnatanbrayan.domain.Pagamento;
import br.com.johnatanbrayan.domain.PagamentoComBoleto;
import br.com.johnatanbrayan.domain.PagamentoComCartao;
import br.com.johnatanbrayan.domain.Pedido;
import br.com.johnatanbrayan.domain.Produto;
import br.com.johnatanbrayan.domain.enums.EstadoPagamento;
import br.com.johnatanbrayan.domain.enums.TipoCliente;
import br.com.johnatanbrayan.repository.CategoriaRepository;
import br.com.johnatanbrayan.repository.CidadeRepository;
import br.com.johnatanbrayan.repository.ClienteRepository;
import br.com.johnatanbrayan.repository.EnderecoRepository;
import br.com.johnatanbrayan.repository.EstadoRepository;
import br.com.johnatanbrayan.repository.PagamentoRepository;
import br.com.johnatanbrayan.repository.PedidoRepository;
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
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	PedidoRepository pedidoRepository;
	
	@Autowired
	PagamentoRepository pagamentoRepository;

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
		
		  
		e1.getCidade().addAll(Arrays.asList(c1));
		e2.getCidade().addAll(Arrays.asList(c2,c3));

		
		estadoRepository.saveAll(Arrays.asList(e1,e2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "938438291893", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("992659789","30821135"));
		
		Endereco end1 = new Endereco(null, "Rua Flores", "300", "Jardim", "Apto 203", "38220834", c1, cli1);
		Endereco end2 = new Endereco(null, "Avenida Matos", "105", "Centro", "Sala 800", "38777012", c2, cli1);
		
		cli1.getEnderecos().addAll(Arrays.asList(end1,end2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(end1,end2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, end1);
		Pedido ped2 = new Pedido(null,sdf.parse("10/10/2017 19:35"), cli1, end2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		Pagamento pag1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		Pagamento pag2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 12:04"), null);
		
		ped1.setPagamento(pag1);
		ped2.setPagamento(pag2);
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pag1,pag2));
		
	}
}