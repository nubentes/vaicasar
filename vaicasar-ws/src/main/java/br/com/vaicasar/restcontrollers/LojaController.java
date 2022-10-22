package br.com.vaicasar.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.vaicasar.model.entity.Loja;
import br.com.vaicasar.model.services.LojaService;

@RestController
@RequestMapping(value = "/loja", produces = "application/json")
public class LojaController {
	
	@Autowired
	private LojaService lojaService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/todos")
	public List<Loja> obterTodos() {
		return lojaService.obterTodos();
	}

}
