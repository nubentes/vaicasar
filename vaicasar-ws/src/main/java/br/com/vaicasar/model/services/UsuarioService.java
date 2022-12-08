package br.com.vaicasar.model.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.vaicasar.exceptions.RulesException;
import br.com.vaicasar.model.entity.Usuario;
import br.com.vaicasar.model.repositories.UsuarioRepository;
import br.com.vaicasar.security.CriptografiaMd5;

@Service
@Transactional(readOnly = false)
public class UsuarioService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario validarLogin(Usuario u) throws RulesException {
		Usuario usuario = obterPorEmail(u.getEmail());
		
		if (!senhaConfere(usuario, CriptografiaMd5.encript(u.getEmail(), u.getSenha()))) {
			throw new RulesException("Usuário não cadastrado ou senha inválida.");
		}
		
		return usuario;
	}
	
	private boolean senhaConfere(Usuario usuario, String senha) {
		if (usuario == null) {
			return false;
		}

		return senha.equals(usuario.getSenha());
	}
	
	public Usuario obterPorEmail(String email) {
		return usuarioRepository.obterUsuarioPorEmail(email);
	}
	
	public boolean possuiEmailCadastrado(String email) {
		Integer possui = usuarioRepository.possuiEmailCadastrado(email);
		
		return possui > 0 ? true : false;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.obterUsuarioPorEmail(email);
		if (usuario == null)
			throw new UsernameNotFoundException("Usuário não cadastrado ou senha inválida.");

		List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("Usuário"));

		return new org.springframework.security.core.userdetails.User(usuario.getEmail(), usuario.getSenha(), authorities);
	}
}
