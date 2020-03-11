package br.com.johnatanbrayan.domain;

import javax.persistence.Entity;

import br.com.johnatanbrayan.domain.enums.EstadoPagamento;

@Entity
public class PagamentoComCartao extends Pagamento{
	private static final long serialVersionUID = 1L;
	
	private Integer numeroDeParcelas;
	
	public PagamentoComCartao() {}
	public PagamentoComCartao(Integer id, EstadoPagamento estadoPagamento, Pedido pedido, Integer numeroDeParcelas) {
		super(id,estadoPagamento, pedido);
		this.numeroDeParcelas = numeroDeParcelas;
	}
	
	public Integer getNumeroDeParcelas() { return this.numeroDeParcelas; }
	public void setNumeroDeParcelas(Integer numeroDeParcelas) { this.numeroDeParcelas = numeroDeParcelas; }
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((numeroDeParcelas == null) ? 0 : numeroDeParcelas.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PagamentoComCartao other = (PagamentoComCartao) obj;
		if (numeroDeParcelas == null) {
			if (other.numeroDeParcelas != null)
				return false;
		} else if (!numeroDeParcelas.equals(other.numeroDeParcelas))
			return false;
		return true;
	}
}
