package br.com.vaicasar.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "CRONOGRAMA")
public class Cronograma implements Serializable {

	private static final long serialVersionUID = 6334180827765324724L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CRONOGRAMA")
	private Long id;
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PESSOA")
	private Pessoa pessoa;
	
	@Column(name = "DATA_PREVISTA")
	@JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT-3")
	private Date dataPrevista;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cronograma", orphanRemoval = true)
	private List<Tarefa> tarefas;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Date getDataPrevista() {
		return dataPrevista;
	}

	public void setDataPrevista(Date dataPrevista) {
		this.dataPrevista = dataPrevista;
	}

	public List<Tarefa> getTarefas() {
		return tarefas;
	}

	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}

}
