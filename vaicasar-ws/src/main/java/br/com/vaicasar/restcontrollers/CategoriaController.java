package br.com.vaicasar.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.vaicasar.model.entity.Categoria;
import br.com.vaicasar.model.services.CategoriaService;

@RestController
@RequestMapping(value = "/categoria", produces = "application/json")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/todos")
	public List<Categoria> obterTodos() {
		return categoriaService.obterTodos();
	}
}
