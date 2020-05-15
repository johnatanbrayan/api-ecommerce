package br.com.johnatanbrayan.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.johnatanbrayan.domain.Cliente;

public class ClienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotEmpty(message="Nome não pode estar vazio")
	@Length(min=5, max=80, message="Tamanho tem que ser entre 5 e 80 caracteres")
	private String nome;
	
	@NotEmpty(message="Email não pode estar vazio")
	@Email(message="Email inválido")
	private String email;
	
	public ClienteDTO() {}

	public ClienteDTO(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.email = cliente.getEmail();
	}
	public ClienteDTO(Long id, String nome, String email) {
		this.id = id;
		this.nome = nome;
		this.email = email; 
	}

	
	public Long getId() { return this.id; }
	public void setId(Long id) { this.id = id; }
	
	public String getNome() { return this.nome; }
	public void setNome(String nome) { this.nome = nome; }
	
	public String getEmail() { return this.email; }
	public void setEmail(String email) { this.email = email; }
}
