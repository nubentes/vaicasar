package br.com.vaicasar.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.vaicasar.model.entity.Loja;
import br.com.vaicasar.model.repositories.LojaRepository;

@Service
@Transactional(readOnly = false)
public class LojaService {

	@Autowired
	private LojaRepository lojaRepository;
	
	public List<Loja> obterTodos() {
		return lojaRepository.findAll();
	}
	
	public Loja obterPorId(Long idLoja) {
		return lojaRepository.obterPorId(idLoja);
	}
	
	public List<Loja> obterPorCategoria(Long idCategoria) {
		return lojaRepository.obterPorCategoria(idCategoria);
	}
	
	public Loja salvar(Loja loja) {
		return lojaRepository.save(loja);
	}
	
}
