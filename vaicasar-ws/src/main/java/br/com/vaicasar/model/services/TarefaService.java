package br.com.vaicasar.model.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.vaicasar.dto.CriarTarefaDTO;
import br.com.vaicasar.model.entity.Cronograma;
import br.com.vaicasar.model.entity.Tarefa;
import br.com.vaicasar.model.entity.TemplateTarefas;
import br.com.vaicasar.model.repositories.TarefaRepository;

@Service
@Transactional(readOnly = false)
public class TarefaService {

	@Autowired
	private TarefaRepository tarefaRepository;
	@Autowired
	private TemplateTarefasService templateTarefasService;
	@Autowired
	private CronogramaService cronogramaService;
	
	public Cronograma criarTarefasBase(Cronograma cronograma) {
		List<TemplateTarefas> tarefas = templateTarefasService.findAll();
		
		for (TemplateTarefas template : tarefas) {
			Tarefa tarefa = new Tarefa();
			tarefa.setDescricao(template.getDescricao());
			tarefa.setDataPrevista(obterDataPrevistaTarefa(cronograma.getDataPrevista(), template.getProcentagem()));
			tarefa.setCronograma(cronograma);
			
			tarefaRepository.save(tarefa);
		}
		
		return cronogramaService.obterPorId(cronograma.getId());
	}
	
	private Date obterDataPrevistaTarefa(Date dataCasamento, double porcentagem) {
		Long tempo = dataCasamento.getTime() - new Date().getTime();
		double data = dataCasamento.getTime() - (tempo * (porcentagem/100));
		return new Date((long) data);
	}
	
	public Cronograma criarTarefa(CriarTarefaDTO criarTarefaDTO) {
		Cronograma cronograma = cronogramaService.findById(criarTarefaDTO.getIdCronograma());
		
		Tarefa tarefa = criarTarefaDTO.getTarefa();
		tarefa.setCronograma(cronograma);
		
		tarefaRepository.save(tarefa);
		
		return cronogramaService.obterPorId(criarTarefaDTO.getIdCronograma());
	}
}
