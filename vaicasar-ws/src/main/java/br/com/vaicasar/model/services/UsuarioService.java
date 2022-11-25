package br.com.vaicasar.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.vaicasar.model.repositories.UsuarioRepository;

@Service
@Transactional(readOnly = false)
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public boolean possuiEmailCadastrado(String email) {
		Integer possui = usuarioRepository.possuiEmailCadastrado(email);
		
		return possui > 0 ? true : false;
	}
}
