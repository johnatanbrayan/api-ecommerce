package br.com.johnatanbrayan.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer id;
	public String logradouro;
	public String numero;
	public String bairro;
	public String complemento;
	public String cep;
	
	@ManyToOne()
	@JoinColumn(name="cidade_id")
	public Cidade cidade;
	
	public Endereco() {}
	
	public Endereco(Integer id, String logradouro, String numero, String bairro, String complemento, String cep, Cidade cidade) {
		this.id = id;
		this.logradouro = logradouro;
		this.numero = numero;
		this.bairro = bairro;
		this.complemento = complemento;
		this.cep = cep;
		this.cidade = cidade;
	}
	
	public Integer getId() { return this.id; }
	public void setId(Integer id) { this.id = id; }
	
	public String getLogradouro() { return this.logradouro; }
	public void setLogradouro(String logradouro) { this.logradouro = logradouro; }
	
	public String getNumero() { return this.numero; }
	public void setNumero(String numero) {this.numero = numero; }
	
	public String getBairro() { return this.bairro; }
	public void setBairro(String bairro) { this.bairro = bairro; }
	
	public String getComplemento() { return this.complemento; }
	public void setComplemento(String complemento) {this.complemento = complemento; }
	
	public String getCep() { return this.cep; }
	public void setCep(String cep) { this.cep = cep; }
	
	public Cidade getCidade() { return this.cidade; }
	public void setCidade(Cidade cidade) { this.cidade = cidade; }

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
		Endereco other = (Endereco) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
