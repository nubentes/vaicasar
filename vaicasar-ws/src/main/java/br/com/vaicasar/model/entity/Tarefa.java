package br.com.vaicasar.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "TAREFA")
public class Tarefa implements Serializable {

	private static final long serialVersionUID = 1854747093702607467L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_TAREFA")
	private Long id;
	
	@Column(name = "DESCRICAO")
	private String descricao;
	
	@Column(name = "DATA_PREVISTA")
	@JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT-3")
	private Date dataPrevista;
	
	@Column(name = "DATA_CONCLUSAO")
	@JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT-3")
	private Date dataConclusao;
	
	@Column(name = "VALOR")
	private double valor;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CRONOGRAMA")
	private Cronograma cronograma;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_LOJA")
	private Loja loja;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataPrevista() {
		return dataPrevista;
	}

	public void setDataPrevista(Date dataPrevista) {
		this.dataPrevista = dataPrevista;
	}

	public Date getDataConclusao() {
		return dataConclusao;
	}

	public void setDataConclusao(Date dataConclusao) {
		this.dataConclusao = dataConclusao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Cronograma getCronograma() {
		return cronograma;
	}

	public void setCronograma(Cronograma cronograma) {
		this.cronograma = cronograma;
	}

	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}

}
