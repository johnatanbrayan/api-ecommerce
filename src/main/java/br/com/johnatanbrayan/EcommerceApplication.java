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
import br.com.johnatanbrayan.domain.ItemPedido;
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
import br.com.johnatanbrayan.repository.ItemPedidoRepository;
import br.com.johnatanbrayan.repository.PagamentoRepository;
import br.com.johnatanbrayan.repository.PedidoRepository;
import br.com.johnatanbrayan.repository.ProdutoRepository;

@SpringBootApplication
public class EcommerceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		/*--------------------------------------------------------------*/
		Categoria categoria1 = new Categoria(null,"Informática");
		Categoria categoria2 = new Categoria(null,"Escritório");
		Categoria categoria3 = new Categoria(null,"Cama mesa e banho");
		Categoria categoria4 = new Categoria(null,"Eletrônicos");
		Categoria categoria5 = new Categoria(null,"Jardinagem");
		Categoria categoria6 = new Categoria(null,"Decoração");
		Categoria categoria7 = new Categoria(null,"Perfumaria");
				
		Produto produto1 = new Produto(null,"Computador",2000.00);
		Produto produto2 = new Produto(null,"Impressora",800.00);
		Produto produto3 = new Produto(null,"Mouse",80.00);
		Produto produto4 = new Produto(null,"Mesa de escritório",300.00);
		Produto produto5 = new Produto(null,"Toalha",50.00);
		Produto produto6 = new Produto(null,"Colcha",200.00);
		Produto produto7 = new Produto(null,"TV true color",1200.00);
		Produto produto8 = new Produto(null,"Roçadeira",800.00);
		Produto produto9 = new Produto(null,"Abajour", 100.00);
		Produto produto10 = new Produto(null,"Pendente", 180.00);
		Produto produto11 = new Produto(null,"Shamppo",90.00);
		
		
		categoria1.getProdutos().addAll(Arrays.asList(produto1,produto2,produto3));
		categoria2.getProdutos().addAll(Arrays.asList(produto2));
		categoria3.getProdutos().addAll(Arrays.asList(produto5,produto6));
		categoria4.getProdutos().addAll(Arrays.asList(produto1,produto2,produto3,produto7));
		categoria5.getProdutos().addAll(Arrays.asList(produto8));
		categoria6.getProdutos().addAll(Arrays.asList(produto9,produto10));
		categoria7.getProdutos().addAll(Arrays.asList(produto11));
		
		produto1.getCategorias().addAll(Arrays.asList(categoria1));
		produto2.getCategorias().addAll(Arrays.asList(categoria1,categoria2));
		produto3.getCategorias().addAll(Arrays.asList(categoria1));
		produto4.getCategorias().addAll(Arrays.asList(categoria2));
		produto5.getCategorias().addAll(Arrays.asList(categoria3));
		produto6.getCategorias().addAll(Arrays.asList(categoria3));
		produto7.getCategorias().addAll(Arrays.asList(categoria4));
		produto8.getCategorias().addAll(Arrays.asList(categoria5));
		produto9.getCategorias().addAll(Arrays.asList(categoria6));
		produto10.getCategorias().addAll(Arrays.asList(categoria6));
		produto11.getCategorias().addAll(Arrays.asList(categoria7));

		
		categoriaRepository.saveAll(Arrays.asList(categoria1,categoria2,categoria3,categoria4,categoria5,categoria6,categoria7));	
		produtoRepository.saveAll(Arrays.asList(produto1,produto2,produto3,produto4,produto5,produto6,produto7,produto8,produto9,produto10,produto11));
		/*--------------------------------------------------------------*/

		/*--------------------------------------------------------------*/		
		Estado estado1 = new Estado(null,"Minas Gerais");
		Estado estado2 = new Estado(null,"São Paulo");
		
		Cidade cidade1 = new Cidade(null,"Urbelândia",estado1);
		Cidade cidade2 = new Cidade(null,"São Paulo",estado2);
		Cidade cidade3 = new Cidade(null,"Campinas",estado2);	
		  
		estado1.getCidade().addAll(Arrays.asList(cidade1));
		estado2.getCidade().addAll(Arrays.asList(cidade2,cidade3));
		
		estadoRepository.saveAll(Arrays.asList(estado1,estado2));
		cidadeRepository.saveAll(Arrays.asList(cidade1,cidade2,cidade3));
		/*--------------------------------------------------------------*/

		/*--------------------------------------------------------------*/
		Cliente cliente1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "938438291893", TipoCliente.PESSOAFISICA);
		
		cliente1.getTelefones().addAll(Arrays.asList("992659789","30821135"));
		
		Endereco endereco1 = new Endereco(null, "Rua Flores", "300", "Jardim", "Apto 203", "38220834", cidade1, cliente1);
		Endereco endereco2 = new Endereco(null, "Avenida Matos", "105", "Centro", "Sala 800", "38777012", cidade2, cliente1);
		
		cliente1.getEnderecos().addAll(Arrays.asList(endereco1,endereco2));
		
		clienteRepository.saveAll(Arrays.asList(cliente1));
		enderecoRepository.saveAll(Arrays.asList(endereco1,endereco2));
		/*--------------------------------------------------------------*/
		
		/*--------------------------------------------------------------*/
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido pedido1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cliente1, endereco1);
		Pedido pedido2 = new Pedido(null,sdf.parse("10/10/2017 19:35"), cliente1, endereco2);
		
		cliente1.getPedidos().addAll(Arrays.asList(pedido1,pedido2));
		
		Pagamento pagamento1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, pedido1, 6);
		Pagamento pagamento2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, pedido2, sdf.parse("20/10/2017 12:04"), null);
		
		pedido1.setPagamento(pagamento1);
		pedido2.setPagamento(pagamento2);
		
		pedidoRepository.saveAll(Arrays.asList(pedido1,pedido2));
		pagamentoRepository.saveAll(Arrays.asList(pagamento1,pagamento2));
		/*--------------------------------------------------------------*/
		
		/*--------------------------------------------------------------*/
		ItemPedido itemPedido1 = new ItemPedido(0.00, 1, 2000.00, pedido1, produto1);
		ItemPedido itemPedido2 = new ItemPedido(0.00, 2, 80.00, pedido1, produto3);
		ItemPedido itemPedido3 = new ItemPedido(100.00, 1, 800.00, pedido2, produto2);
		
		pedido1.getItens().addAll(Arrays.asList(itemPedido1,itemPedido2));
		pedido2.getItens().addAll(Arrays.asList(itemPedido3));
		
		produto1.getItens().addAll(Arrays.asList(itemPedido1));
		produto2.getItens().addAll(Arrays.asList(itemPedido3));
		produto3.getItens().addAll(Arrays.asList(itemPedido2));
		
		itemPedidoRepository.saveAll(Arrays.asList(itemPedido1,itemPedido2,itemPedido3));
		/*--------------------------------------------------------------*/
	}
}