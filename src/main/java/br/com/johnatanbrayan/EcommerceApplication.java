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
		Categoria categoria1 = new Categoria(null,"Escritório");
		Categoria categoria2 = new Categoria(null,"Informática");
				
		Produto produto1 = new Produto(null,"Computador",2000.00);
		Produto produto2 = new Produto(null,"Impressora",800.00);
		Produto produto3 = new Produto(null,"Mouse",80.00);
		
		categoria1.getProdutos().addAll(Arrays.asList(produto1,produto2,produto3));
		categoria2.getProdutos().addAll(Arrays.asList(produto2));
		
		produto1.getCategorias().addAll(Arrays.asList(categoria1));
		produto2.getCategorias().addAll(Arrays.asList(categoria1,categoria2));
		produto3.getCategorias().addAll(Arrays.asList(categoria1));
		
		categoriaRepository.saveAll(Arrays.asList(categoria1,categoria2));	
		produtoRepository.saveAll(Arrays.asList(produto1,produto2,produto3));
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