package br.com.vaicasar.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.vaicasar.model.entity.Categoria;
import br.com.vaicasar.model.entity.Loja;
import br.com.vaicasar.model.entity.Preferencias;
import br.com.vaicasar.model.services.PreferenciasService;

@RestController
@RequestMapping(value = "/preferencias", produces = "application/json")
public class PreferenciasController {

	@Autowired
	private PreferenciasService preferenciasService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/adicionar")
	public Preferencias adicionar(@RequestBody(required = true) Preferencias preferencias) {
		return preferenciasService.adicionar(preferencias);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/remover-loja")
	public String removerLoja(@RequestParam(name = "idPessoa", required = true) Long idPessoa,
			@RequestParam(name = "idLoja", required = true) Long idLoja) {
		return preferenciasService.removerLoja(idPessoa, idLoja);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/categoria")
	public List<Loja> obterPorCategoria(@RequestParam(name = "idCategoria", required = true) Long idCategoria) {
		return preferenciasService.obterPorCategoria(idCategoria);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/categorias")
	public List<Categoria> obterCategoriasDaListaPorPessoa(@RequestParam(name = "idPessoa", required = true) Long idPessoa) {
		return preferenciasService.obterCategoriasDaListaPorPessoa(idPessoa);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/limpar-lista")
	public String limparLista(@RequestParam(name = "idPessoa", required = true) Long idPessoa) {
		return preferenciasService.limparLista(idPessoa);
	}
	
}
