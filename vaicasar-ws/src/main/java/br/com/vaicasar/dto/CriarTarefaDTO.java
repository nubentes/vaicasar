package br.com.vaicasar.dto;

import java.io.Serializable;

import br.com.vaicasar.model.entity.Tarefa;

public class CriarTarefaDTO implements Serializable {

	private static final long serialVersionUID = -614205397687911953L;

	private Long idCronograma;
	private Tarefa tarefa;
	
	public CriarTarefaDTO() {
		
	}
	
	public Long getIdCronograma() {
		return idCronograma;
	}
	
	public void setIdCronograma(Long idCronograma) {
		this.idCronograma = idCronograma;
	}
	
	public Tarefa getTarefa() {
		return tarefa;
	}
	
	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}
	
	
}
