package br.com.johnatanbrayan.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.johnatanbrayan.domain.enums.EstadoPagamento;

@Entity
public class PagamentoComBoleto extends Pagamento{
	private static final long serialVersionUID = 1L;
	
	@Temporal(TemporalType.DATE)
	private Date dataVencimento;
	
	@Temporal(TemporalType.DATE)
	private Date dataPagamento;
	
	public PagamentoComBoleto() {}
	public PagamentoComBoleto(Integer id, EstadoPagamento estadoPagamento, Date dataVencimento, Date dataPagamento) {
		super(id, estadoPagamento);
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
	}
	
	public Date getDataVencimento() { return this.dataVencimento; }
	public void setDataVencimento(Date dataVencimento) { this.dataVencimento = dataVencimento; }
	
	public Date getDataPagamento() { return this.dataPagamento; }
	public void setDataPagamento(Date dataPagamento) { this.dataPagamento = dataPagamento; }
}
