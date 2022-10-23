package br.com.vaicasar.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.vaicasar.dto.CriarTarefaDTO;
import br.com.vaicasar.model.entity.Cronograma;
import br.com.vaicasar.model.services.TarefaService;

@RestController
@RequestMapping(value = "/tarefa", produces = "application/json")
public class TarefaController {

	@Autowired
	private TarefaService tarefaService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/criar-base")
	public Cronograma criarTarefasBase(@RequestBody(required = true) Cronograma cronograma) {
		return tarefaService.criarTarefasBase(cronograma);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/criar-tarefa")
	public Cronograma criarTarefa(@RequestBody(required = true) CriarTarefaDTO criarTarefaDTO) {
		return tarefaService.salvar(criarTarefaDTO);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/editar-tarefa")
	public Cronograma editarTarefa(@RequestBody(required = true) CriarTarefaDTO criarTarefaDTO) {
		return tarefaService.salvar(criarTarefaDTO);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/excluir-tarefa")
	public String excluirTarefa(@RequestParam(name = "id", required = true) Long id) {
		return tarefaService.excluir(id);
	}
}
