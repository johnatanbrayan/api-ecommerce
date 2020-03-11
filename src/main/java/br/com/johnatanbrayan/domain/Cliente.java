package br.com.johnatanbrayan.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import br.com.johnatanbrayan.domain.enums.TipoCliente;

@Entity
public class Cliente implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String email;
	private String cpfOuCnpj;
	private Integer tipoCliente;
	
	
	@ElementCollection
	@CollectionTable(name="telefone")
	private Set<String> telefones = new HashSet<>();
	
	@OneToMany(mappedBy="cliente")
	private List<Endereco> enderecos = new ArrayList<>();
	
	@OneToMany(mappedBy="cliente")
	private List<Pedido> pedidos = new ArrayList<>();
	
	public Cliente() {}
	
	public Cliente(Integer id, String nome, String email, String cpfOuCnpj, TipoCliente tipoCliente) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.tipoCliente = tipoCliente.getCod();
	}
	
	public Integer getCliente() { return this.id; }
	public void setCliente(Integer id) { this.id = id; }
	
	public String getNome() { return this.nome; }
	public void setNome(String nome) { this.nome = nome; }
	
	public String getEmail() { return this.email; }
	public void setEmail(String email) { this.email = email; }
	
	public String getCpfOuCnpj() { return this.cpfOuCnpj; }
	public void setCpfOuCnpj(String cpfOuCnpj) { this.cpfOuCnpj = cpfOuCnpj; }
	
	public TipoCliente getTipoCliente() { return TipoCliente.toEnum(tipoCliente); }
	public void setTipoCliente(TipoCliente tipoCliente) {this.tipoCliente = tipoCliente.getCod(); }
	
	public Set<String> getTelefones() { return this.telefones; }
	public void setTelefones(Set<String> telefones) { this.telefones = telefones; }
	
	public List<Endereco> getEnderecos() { return this.enderecos; }
	public void setEnderecos(List<Endereco> enderecos) { this.enderecos = enderecos; }
	
	public List<Pedido> getPedidos() { return this.pedidos; }
	public void setPedidos(List<Pedido> pedidos) { this.pedidos = pedidos; }

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
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}