package br.com.vaicasar.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CRONOGRAMA")
public class Cronograma extends AuditEntity implements Serializable {

	private static final long serialVersionUID = 6334180827765324724L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CRONOGRAMA")
	private Long id;
	
	@Column(name = "DATA_PREVISTA")
	private Date dataPrevista;
	
	@OneToMany(mappedBy = "cronograma", fetch = FetchType.LAZY, orphanRemoval = true)
	private Set<Tarefa> tarefas;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getDataPrevista() {
		return dataPrevista;
	}
	
	public void setDataPrevista(Date dataPrevista) {
		this.dataPrevista = dataPrevista;
	}

	public Set<Tarefa> getTarefas() {
		return tarefas;
	}

	public void setTarefas(Set<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}

}
