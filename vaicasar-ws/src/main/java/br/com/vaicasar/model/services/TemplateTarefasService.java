package br.com.vaicasar.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.vaicasar.model.entity.TemplateTarefas;
import br.com.vaicasar.model.repositories.TemplateTarefasRepository;

@Service
@Transactional(readOnly = false)
public class TemplateTarefasService {

	@Autowired
	private TemplateTarefasRepository templateTarefasRepository;
	
	public List<TemplateTarefas> findAll() {
		return templateTarefasRepository.findAll();
	}
}
