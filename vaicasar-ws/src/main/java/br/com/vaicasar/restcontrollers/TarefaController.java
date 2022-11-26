package br.com.vaicasar.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.vaicasar.dto.CriarTarefaDTO;
import br.com.vaicasar.model.entity.Tarefa;
import br.com.vaicasar.model.services.TarefaService;

@RestController
@RequestMapping(value = "/tarefa", produces = "application/json")
public class TarefaController extends AbstractRestController {

	@Autowired
	private TarefaService tarefaService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/criar-tarefa")
	public Tarefa criarTarefa(@RequestBody(required = true) CriarTarefaDTO criarTarefaDTO) {
		return tarefaService.criarTarefa(criarTarefaDTO);
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public String excluirTarefa(@RequestParam(name = "id", required = true) Long id) {
		return tarefaService.excluirTarefa(id);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/concluir")
	public Tarefa marcarConcluida(@RequestParam(name = "id", required = true) Long idTarefa) {
		return tarefaService.marcarConcluida(idTarefa);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/desmarcar")
	public Tarefa desmarcarConcluida(@RequestParam(name = "id", required = true) Long idTarefa) {
		return tarefaService.desmarcarConcluida(idTarefa);
	}
	
//	@RequestMapping(method = RequestMethod.PUT, value = "/editar-tarefa")
//	public Cronograma editarTarefa(@RequestBody(required = true) CriarTarefaDTO criarTarefaDTO) {
//		return tarefaService.salvar(criarTarefaDTO);
//	}
	

}
