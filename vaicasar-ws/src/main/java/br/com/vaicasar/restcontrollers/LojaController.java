package br.com.vaicasar.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.vaicasar.model.entity.Loja;
import br.com.vaicasar.model.services.LojaService;

@RestController
@RequestMapping(value = "/loja", produces = "application/json")
public class LojaController extends AbstractRestController {
	
	@Autowired
	private LojaService lojaService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/todos")
	public List<Loja> obterTodos() {
		return lojaService.obterTodos();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public Loja obterPorId(@RequestParam(name = "idLoja", required = true) Long idLoja) {
		return lojaService.obterPorId(idLoja);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/categoria")
	public List<Loja> obterPorCategoria(@RequestParam(name = "idCategoria", required = true) Long idCategoria) {
		return lojaService.obterPorCategoria(idCategoria);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/salvar")
	public Loja salvarLoja(@RequestBody(required = true) Loja loja) {
		return lojaService.salvar(loja);
	}

}
