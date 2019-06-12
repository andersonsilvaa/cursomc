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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.cursomc.domain.enums.TipoCliente;

@Entity
@Table(name="CLIENTE")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	@Column(name="NOME")
	private String nome;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="CPFOUCNPJ")
	private String cpfOuCnpj;
	
	@Column(name="TIPO")
	private Integer tipo;
	
	@JsonManagedReference
	@OneToMany(mappedBy="cliente", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE }, orphanRemoval = true)
	private List<Endereco> enderecos;
	
	@JsonManagedReference
	@OneToMany(mappedBy="cliente", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE }, orphanRemoval = true)
	private List<Telefone> telefones;
	
	@OneToMany(mappedBy="cliente", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE }, orphanRemoval = true)
	private List<Pedido> pedidos;
	
	/*****************************************************
	 *	CONSTRUTORES
	 ****************************************************/
	
	public Cliente() {
	}
	
	public Cliente(Long id, String nome, String email, String cpfOuCnpj, TipoCliente tipoCliente) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.tipo = tipoCliente.getCodigo();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public TipoCliente getTipo() {
		return TipoCliente.toEnum(this.tipo);
	}

	public void setTipo(TipoCliente tipo) {
		this.tipo = tipo.getCodigo();
	}

	public List<Endereco> getEnderecos() {
		if(this.enderecos==null) {
			this.enderecos = new ArrayList<Endereco>();
		}
		return Collections.unmodifiableList(enderecos);
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}
	
	public void addEndereco(Endereco endereco) {
		if(this.enderecos==null) {
			this.enderecos = new ArrayList<Endereco>();
		}
		if(endereco!=null) {
			endereco.setCliente(this);
			this.enderecos.add(endereco);
		}
	}
	
	public List<Telefone> getTelefones() {
		if(this.telefones==null) {
			this.telefones = new ArrayList<Telefone>();
		}
		return Collections.unmodifiableList(telefones);
	}
	
	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}
	
	public void addTelefone(Telefone telefone) {
		if(this.telefones==null) {
			this.telefones = new ArrayList<Telefone>();
		}
		if(telefone!=null) {
			telefone.setCliente(this);
			this.telefones.add(telefone);
		}
	}
	
	public List<Pedido> getPedidos() {
		if(this.pedidos==null) {
			this.pedidos = new ArrayList<Pedido>();
		}
		return Collections.unmodifiableList(pedidos);
	}
	
	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	public void addPedido(Pedido pedido) {
		if(this.pedidos==null) {
			this.pedidos = new ArrayList<Pedido>();
		}
		if(pedido!=null) {
			pedido.setCliente(this);
			this.pedidos.add(pedido);
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
		Cliente other = (Cliente) obj;
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
		return "Cliente [id=" + id + ", nome=" + nome + ", email=" + email + ", cpfOuCnpj=" + cpfOuCnpj + ", tipo="
				+ tipo + ", enderecos=" + enderecos + ", telefones=" + telefones + "]";
	}
	
}
