package br.com.vaicasar.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.vaicasar.dto.CronogramaDTO;
import br.com.vaicasar.exceptions.NotAcceptableException;
import br.com.vaicasar.model.entity.Cronograma;
import br.com.vaicasar.model.entity.Pessoa;
import br.com.vaicasar.model.entity.Tarefa;
import br.com.vaicasar.model.repositories.CronogramaRepository;
import br.com.vaicasar.util.Messages;

@Service
@Transactional(readOnly = false)
public class CronogramaService {

	@Autowired
	private PessoaService pessoaService;
	@Autowired
	private CronogramaRepository cronogramaRepository;
	@Autowired
	private TarefaService tarefaService;
	@Autowired
	private Messages messages;
	
	public Cronograma criar(CronogramaDTO cronogramaDto) {
		Pessoa pessoa = pessoaService.findByIdUsuario(cronogramaDto.getUsuario().getId());
		
		if (cronogramaRepository.possuiCronograma(pessoa.getId()) > 0)
			throw new NotAcceptableException(messages.get("MS003"));
		
		Cronograma cronograma = new Cronograma();
		cronograma.setPessoa(pessoa);
		cronograma.setDataPrevista(cronogramaDto.getDataPrevista());
		
		cronograma = cronogramaRepository.save(cronograma);
		
		List<Tarefa> tarefasBase = tarefaService.criarTarefasBase(cronograma);
		
		cronograma.setTarefas(tarefasBase);
		
		return cronogramaRepository.save(cronograma);
	}
	
	public Cronograma obterPorId(Long id) {
		return cronogramaRepository.obterPorId(id);
	}
	
	public Cronograma obterPorIdUsuario(Long idUsuario) {
		return cronogramaRepository.obterPorIdUsuario(idUsuario);
	}
	
	public Cronograma obterPorIdPessoa(Long idPessoa) {
		return cronogramaRepository.obterPorIdPessoa(idPessoa);
	}
	
}
