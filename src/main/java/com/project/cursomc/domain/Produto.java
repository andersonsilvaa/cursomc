package com.project.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="PRODUTO")
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	@Column(name="NOME")
	private String nome;
	
	@Column(name="PRECO")
	private Double preco;
	
	@JsonBackReference
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
	@JoinTable(name = "PRODUTO_CATEGORIA",
		joinColumns = @JoinColumn(name="PRODUTO_ID"),
		inverseJoinColumns = @JoinColumn(name="CATEGORIA_ID")
	)
	private List<Categoria> categorias;
	
	/*****************************************************
	 *	CONSTRUTORES
	 ****************************************************/
	
	public Produto() {
	}
	
	public Produto(Long id, String nome, Double preco) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
	}
	
	/*****************************************************
	 *	METODOS ACESSORES
	 ****************************************************/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	public List<Categoria> getCategorias() {
		if(this.categorias==null){
			this.categorias = new ArrayList<Categoria>();
		}
		return Collections.unmodifiableList(categorias);
	}
	
	public void setCategorias(List<Categoria> categorias) {
		
		this.categorias = categorias;
	}
	
	public void addCategoria(Categoria categoria) {
		if(this.categorias==null){
			this.categorias = new ArrayList<Categoria>();
		}
		if(categoria!=null) {
			this.categorias.add(categoria);
		}
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
		Produto other = (Produto) obj;
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
		return "Produto [id=" + id + ", nome=" + nome + ", preco=" + preco + ", categorias=" + categorias + "]";
	}
	
}
