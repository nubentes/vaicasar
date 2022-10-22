package br.com.vaicasar.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.vaicasar.model.entity.Pessoa;
import br.com.vaicasar.model.repositories.PessoaRepository;

@Service
@Transactional(readOnly = false)
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Pessoa findByIdUsuario(Long idUsuario) {
		return pessoaRepository.findByIdUsuario(idUsuario);
	}

}
