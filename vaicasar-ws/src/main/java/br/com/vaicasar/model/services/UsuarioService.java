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

import br.com.vaicasar.dto.UsuarioDTO;
import br.com.vaicasar.exceptions.NotAcceptableException;
import br.com.vaicasar.exceptions.RulesException;
import br.com.vaicasar.model.entity.Pessoa;
import br.com.vaicasar.model.entity.Usuario;
import br.com.vaicasar.model.repositories.UsuarioRepository;
import br.com.vaicasar.security.CriptografiaMd5;

@Service
@Transactional(readOnly = false)
public class UsuarioService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private PessoaService pessoaService;
	
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
	
	public Usuario salvar(UsuarioDTO dto) {
		Usuario user = new Usuario();
		user.setId((long) 0);
		user.setEmail(dto.getEmail());
		user.setSenha(dto.getSenha());
		
		Usuario usuario = obterPorEmail(dto.getEmail());
		
		if (usuario != null && user.getId() == 0)
			throw new NotAcceptableException("Já existe um usuário cadastrado com o email informado.");
		
		user.setSenha(CriptografiaMd5.encript(user.getEmail(), user.getSenha()));
		
		if (user.getEmail() == null || user.getEmail().equals(""))
			throw new NotAcceptableException("O campo email está vazio.");
		
		Usuario u = usuarioRepository.save(user);
		
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(dto.getNome());
		pessoa.setTelefone(dto.getTelefone());
		pessoa.setUsuario(u);
		pessoaService.salvar(pessoa);
		
		return u;
	}
	
	public Usuario editar(UsuarioDTO dto) {
		Usuario usuario = new Usuario();
		usuario.setId(dto.getIdUsuario());
		usuario.setEmail(dto.getEmail());
		usuario.setSenha(CriptografiaMd5.encript(usuario.getEmail(), dto.getSenha()));
		
		Usuario u = usuarioRepository.save(usuario);
		
		Pessoa pessoa = pessoaService.findByIdUsuario(u.getId());
		pessoa.setNome(dto.getNome());
		pessoa.setTelefone(dto.getTelefone());
		pessoa.setUsuario(u);
		
		pessoaService.salvar(pessoa);
		
		return u;
	}
	
	public Usuario alterarSenha(Usuario u) {
		Usuario usuario = obterPorEmail(u.getEmail());
		
		if (usuario != null)
			usuario.setSenha(CriptografiaMd5.encript(usuario.getEmail(), u.getSenha()));
		else
			throw new NotAcceptableException("Usuário não cadastrado.");
		
		return usuarioRepository.save(usuario);
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
