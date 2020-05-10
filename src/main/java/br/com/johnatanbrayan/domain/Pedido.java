package br.com.johnatanbrayan.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@JsonFormat(pattern = "dd//MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	private Date instance;
	
	@ManyToOne()
	@JoinColumn(name="cliente_id")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name="endereco_id")
	private Endereco enderecoEntrega;
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy="pedido")
	private Pagamento pagamento;
	
	@OneToMany(mappedBy="id.pedido")
	private Set<ItemPedido> itens = new HashSet<>();
	
	public Pedido() {}
	
	public Pedido(Long id, Date instance, Cliente cliente, Endereco enderecoEntrega) {
		this.id = id;
		this.instance = instance;
		this.cliente = cliente;
		this.enderecoEntrega = enderecoEntrega;
		//this.pagamento = pagamento;
	}
	
	public Long getId() { return this.id; }
	public void setId(Long id) { this.id = id; }
	
	public Date getInstance() { return this.instance; }
	public void setInstance(Date instance) { this.instance = instance; }
	
	public Cliente getCliente() { return this.cliente; }
	public void setClinte(Cliente cliente) { this.cliente = cliente; }
	
	public Endereco getEnderecoEntrega() { return this.enderecoEntrega; }
	public void setEnderecoEntrega(Endereco enderecoEntrega) { this.enderecoEntrega = enderecoEntrega; }
	
	public Pagamento getPagamento() { return this.pagamento; }
	public void setPagamento(Pagamento pagamento) { this.pagamento = pagamento; }
	
	public Set<ItemPedido> getItens() { return this.itens; }
	public void setItens(Set<ItemPedido> itens) { this.itens = itens; }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
