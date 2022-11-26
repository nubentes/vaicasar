package br.com.vaicasar.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.vaicasar.model.entity.Categoria;
import br.com.vaicasar.model.services.CategoriaService;

@RestController
@RequestMapping(value = "/categoria", produces = "application/json")
public class CategoriaController extends AbstractRestController {

	@Autowired
	private CategoriaService categoriaService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/todos")
	public List<Categoria> obterTodos() {
		return categoriaService.obterTodos();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/criar")
	public Categoria criarCategoria(@RequestBody(required = true) Categoria categoria) {
		return categoriaService.salvarCategoria(categoria);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/alterar")
	public Categoria alterarCategoria(@RequestBody(required = true) Categoria categoria) {
		return categoriaService.salvarCategoria(categoria);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/excluir")
	public String excluirCategoria(@RequestParam(name = "id", required = true) Long id) {
		return categoriaService.excluirCategoria(id);
	}
}
