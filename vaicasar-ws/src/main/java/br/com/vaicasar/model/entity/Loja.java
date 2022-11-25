package br.com.vaicasar.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "LOJA")
public class Loja implements Serializable {

	private static final long serialVersionUID = 4303345958710765777L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_LOJA")
	private Long id;
	
	@Column(name = "NOME")
	private String nome;
	
	@Column(name = "DESCRICAO")
	private String descricao;
	
	@Column(name = "AVALIACAO")
	private double avaliacao;
	
	@Column(name = "FAIXA_PRECO_INICIAL", columnDefinition="decimal", precision=7, scale=2)
	private BigDecimal precoInicial;
	
	@Column(name = "FAIXA_PRECO_FINAL", columnDefinition="decimal", precision=7, scale=2)
	private BigDecimal precoFinal;
	
	@Column(name = "TELEFONE")
	private String telefone;
	
	@Column(name = "URL_FOTO_PERFIL")
	private String urlFotoPerfil;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, orphanRemoval = true)
	@JoinColumn(name = "ID_ENDERECO")
	private Endereco endereco;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_CATEGORIA")
	private Categoria categoria;
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_USUARIO")
	private Usuario usuario;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "loja", orphanRemoval = true)
	private List<FotosLoja> fotosLoja;

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(double avaliacao) {
		this.avaliacao = avaliacao;
	}

	public BigDecimal getPrecoInicial() {
		return precoInicial;
	}

	public void setPrecoInicial(BigDecimal precoInicial) {
		this.precoInicial = precoInicial;
	}

	public BigDecimal getPrecoFinal() {
		return precoFinal;
	}

	public void setPrecoFinal(BigDecimal precoFinal) {
		this.precoFinal = precoFinal;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getUrlFotoPerfil() {
		return urlFotoPerfil;
	}

	public void setUrlFotoPerfil(String urlFotoPerfil) {
		this.urlFotoPerfil = urlFotoPerfil;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<FotosLoja> getFotosLoja() {
		return fotosLoja;
	}

	public void setFotosLoja(List<FotosLoja> fotosLoja) {
		this.fotosLoja = fotosLoja;
	}
	
}
