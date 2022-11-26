package br.com.vaicasar.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.vaicasar.model.services.UsuarioService;

@RestController
@RequestMapping(value = "/usuario", produces = "application/json")
public class UsuarioController extends AbstractRestController {

	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/email-cadastrado")
	public boolean possuiEmailCadastrado(@RequestParam(name = "email", required = true) String email) {
		return usuarioService.possuiEmailCadastrado(email);
	}
}
