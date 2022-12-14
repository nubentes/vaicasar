package br.com.vaicasar.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.vaicasar.config.TokenProvider;
import br.com.vaicasar.dto.UsuarioDTO;
import br.com.vaicasar.model.entity.Usuario;
import br.com.vaicasar.model.services.UsuarioService;

@RestController
@RequestMapping(value = "/usuario", produces = "application/json")
public class UsuarioController extends AbstractRestController {

	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private TokenProvider tokenProvider;
	@Autowired
	private AuthenticationManager authenticationManager; 
	
	@RequestMapping(method = RequestMethod.POST, path = "/logar")
	public Usuario realizarLogin(@RequestBody(required = true) Usuario u) throws Exception {
		Usuario usuario = usuarioService.validarLogin(u);
		
		
		usuario.setToken(obterToken(usuario.getEmail(), usuario.getSenha()));
		return usuario;
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/criar")
	public Usuario salvar(@RequestBody(required = true) UsuarioDTO usuarioDTO) throws Exception {
		Usuario usuario = usuarioService.salvar(usuarioDTO);
		usuario.setToken(obterToken(usuario.getEmail(), usuario.getSenha()));
		return usuario;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/email-cadastrado")
	public boolean possuiEmailCadastrado(@RequestParam(name = "email", required = true) String email) {
		return usuarioService.possuiEmailCadastrado(email);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/teste")
	public String obterPorCriterio() {
		return "Webservice vaicasar-ws est?? online!";
	}
	
	private String obterToken(String email, String senha) {
		final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, senha));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = tokenProvider.generateToken(authentication);
		return token;
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/editar")
	private Usuario editar(@RequestBody(required = true) UsuarioDTO usuarioDTO) {
		Usuario usuario =  usuarioService.editar(usuarioDTO);
		usuario.setToken(obterToken(usuario.getEmail(), usuario.getSenha()));
		return usuario;
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/alterar")
	private Usuario alterarSenha(@RequestBody(required = true) Usuario usuario) {
		return usuarioService.alterarSenha(usuario);
	}
}
