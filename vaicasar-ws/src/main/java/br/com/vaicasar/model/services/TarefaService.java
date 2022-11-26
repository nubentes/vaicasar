package br.com.vaicasar.model.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.vaicasar.dto.CriarTarefaDTO;
import br.com.vaicasar.exceptions.NotFoundException;
import br.com.vaicasar.model.entity.Cronograma;
import br.com.vaicasar.model.entity.Tarefa;
import br.com.vaicasar.model.entity.TemplateTarefas;
import br.com.vaicasar.model.repositories.CronogramaRepository;
import br.com.vaicasar.model.repositories.TarefaRepository;
import br.com.vaicasar.util.Messages;

@Service
@Transactional(readOnly = false)
public class TarefaService {

	@Autowired
	private TarefaRepository tarefaRepository;
	@Autowired
	private TemplateTarefasService templateTarefasService;
	@Autowired
	private CronogramaRepository cronogramaRepository;
	@Autowired
	private Messages messages;
	
	public List<Tarefa> criarTarefasBase(Cronograma cronograma) {
		List<TemplateTarefas> templateTarefas = templateTarefasService.findAll();
		List<Tarefa> tarefas = new ArrayList<>();
		
		for (TemplateTarefas template : templateTarefas) {
			Tarefa tarefa = new Tarefa();
			tarefa.setTitulo(template.getTitulo());
			tarefa.setDescricao(template.getDescricao());
			tarefa.setDataPrevista(obterDataPrevistaTarefa(cronograma.getDataPrevista(), template.getPorcentagem()));
			tarefa.setCronograma(cronograma);
			
			tarefas.add(tarefa);
		}
		
		return tarefaRepository.saveAll(tarefas);
	}
	
	private Date obterDataPrevistaTarefa(Date dataCasamento, double porcentagem) {
		Long tempo = dataCasamento.getTime() - new Date().getTime();
		double data = dataCasamento.getTime() - (tempo * (porcentagem/100));
		return new Date((long) data);
	}
	
	public Tarefa criarTarefa(CriarTarefaDTO criarTarefaDTO) {
		Cronograma cronograma = cronogramaRepository.obterPorId(criarTarefaDTO.getIdCronograma());
		
		if (cronograma == null)
			throw new NotFoundException(messages.get("MS004"));
		
		Tarefa tarefa = criarTarefaDTO.getTarefa();
		tarefa.setCronograma(cronograma);
		
		return tarefaRepository.save(tarefa);
	}
	
	public String excluirTarefa(Long id) {
		try {
			tarefaRepository.deleteById(id);
			return messages.get("MS005");
		} catch (Exception e) {
			throw new NotFoundException(messages.get("MS006"));
		}
	}
	
	public Tarefa marcarConcluida(Long idTarefa) {
		Tarefa tarefa = tarefaRepository.obterPorId(idTarefa);
		
		if (tarefa == null)
			throw new NotFoundException(messages.get("MS006"));
		
		tarefa.setDataConclusao(new Date());
		
		return tarefaRepository.save(tarefa);
	}
	
	public Tarefa desmarcarConcluida(Long idTarefa) {
		Tarefa tarefa = tarefaRepository.obterPorId(idTarefa);
		
		if (tarefa == null)
			throw new NotFoundException(messages.get("MS006"));
		
		tarefa.setDataConclusao(null);
		
		return tarefaRepository.save(tarefa);
	}
	
}
