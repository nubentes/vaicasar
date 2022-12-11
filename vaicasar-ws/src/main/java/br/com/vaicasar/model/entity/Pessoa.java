package br.com.vaicasar.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "PESSOA")
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 3623772657247629076L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PESSOA")
	private Long id;
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_USUARIO")
	private Usuario usuario;
	
	@Column(name = "NOME")
	private String nome;
	
	@Column(name = "TELEFONE")
	private String telefone;
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "pessoa")
	private Cronograma cronograma;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
