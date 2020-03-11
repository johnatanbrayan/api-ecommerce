package br.com.johnatanbrayan.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer id;
	public Date instance;
	
	@ManyToOne()
	@JoinColumn(name="cliente_id")
	public Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name="endereco_id")
	public Endereco enderecoEntrega;
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy="pedido")
	public Pagamento pagamento;
	
	public Pedido() {}
	
	public Pedido(Integer id, Date instance, Cliente cliente) {
		this.id = id;
		this.instance = instance;
		this.cliente = cliente;
	}
	
	public Integer getId() { return this.id; }
	public void setId(Integer id) { this.id = id; }
	
	public Date getInstance() { return this.instance; }
	public void setInstance(Date instance) { this.instance = instance; }
	
	public Cliente getCliente() { return this.cliente; }
	public void setClinte(Cliente cliente) { this.cliente = cliente; }
	
	public Endereco getEnderecoEntrega() { return this.enderecoEntrega; }
	public void setEnderecoEntrega(Endereco enderecoEntrega) { this.enderecoEntrega = enderecoEntrega; }
	
	public Pagamento getPagamento() { return this.pagamento; }
	public void setPagamento(Pagamento pagamento) { this.pagamento = pagamento; }

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
