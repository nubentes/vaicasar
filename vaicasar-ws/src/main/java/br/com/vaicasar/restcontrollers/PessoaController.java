package br.com.vaicasar.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.vaicasar.model.entity.Pessoa;
import br.com.vaicasar.model.services.PessoaService;

@RestController
@RequestMapping(value = "/pessoa", produces = "application/json")
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;
	
	@RequestMapping(method = RequestMethod.GET)
	public Pessoa obterPorIdUsuario(@RequestParam(name = "idUsuario", required = true) Long idUsuario) {
		return pessoaService.findByIdUsuario(idUsuario);
	}
}
