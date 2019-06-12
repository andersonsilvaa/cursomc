package com.project.cursomc.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="ITEMPEDIDO")
public class ItemPedido implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ItemPedidoPK id;
	
	@Column(name="desconto")
	private Double desconto;
	
	@Column(name="quantidade")
	private Integer quantidade;
	
	@Column(name="PRECO")
	private Double preco;
	
	/*****************************************************
	 *	CONSTRUTORES
	 ****************************************************/
	
	public ItemPedido() {
	}

	public ItemPedido(Pedido pedido, Produto produto, Double desconto, Integer quantidade, Double preco) {
		this.setPedido(pedido);
		this.setProduto(produto);
		this.desconto = desconto;
		this.quantidade = quantidade;
		this.preco = preco;
	}
	
	/*****************************************************
	 *	METODOS ACESSORES
	 ****************************************************/

	public void setPedido(Pedido pedido) {
		if(this.id==null) {
			this.id = new ItemPedidoPK();
		}
		this.id.setPedido(pedido);
	}
	
	public Pedido getPedido() {
		return this.id.getPedido();
	}
	
	public void setProduto(Produto produto) {
		if(this.id==null) {
			this.id = new ItemPedidoPK();
		}
		this.id.setProduto(produto);
	}
	
	public Produto getProduto() {
		return this.id.getProduto();
	}
	
	public ItemPedidoPK getId() {
		return id;
	}

	public void setId(ItemPedidoPK id) {
		this.id = id;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	/*****************************************************
	 *	MÉTODO HASCODE E EQUALS
	 ****************************************************/
	
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
		ItemPedido other = (ItemPedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/*****************************************************
	 *	SOBRESCRITA NO MÉTODO toString
	 ****************************************************/
	
	@Override
	public String toString() {
		return "ItemPedido [id=" + id + ", desconto=" + desconto + ", quantidade=" + quantidade + ", preco=" + preco
				+ "]";
	}
	
}
