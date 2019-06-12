package com.project.cursomc.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.project.cursomc.domain.enums.EstadoPagamento;

@Entity
@Table(name="PAGAMENTOCOMBOLETO")
public class PagamentoComBoleto extends Pagamento {

	private static final long serialVersionUID = 1L;
	
	@Column(name="DATA_VENCIMENTO")
	private Date dataVencimento;
	
	@Column(name="DATA_PAGAMENTO")
	private Date dataPagamento;
	
	/*****************************************************
	 *	CONSTRUTORES
	 ****************************************************/
	
	public PagamentoComBoleto() {
	}	
	 
	public PagamentoComBoleto(Long id, EstadoPagamento estadoPagamento, Pedido pedido, Date dataVencimento, Date dataPagamento) {
		super(id, estadoPagamento, pedido);
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
	}
	
	/*****************************************************
	 *	METODOS ACESSORES
	 ****************************************************/

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

}
