package br.com.vaicasar.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.vaicasar.exceptions.NotAcceptableException;
import br.com.vaicasar.exceptions.NotFoundException;
import br.com.vaicasar.model.entity.Categoria;
import br.com.vaicasar.model.entity.Loja;
import br.com.vaicasar.model.entity.Pessoa;
import br.com.vaicasar.model.entity.Preferencias;
import br.com.vaicasar.model.repositories.LojaRepository;
import br.com.vaicasar.model.repositories.PessoaRepository;
import br.com.vaicasar.model.repositories.PreferenciasRepository;
import br.com.vaicasar.util.Messages;

@Service
@Transactional(readOnly = false)
public class PreferenciasService {

	@Autowired
	private PreferenciasRepository preferenciasRepository;
	@Autowired
	private LojaRepository lojaRepository;
	@Autowired
	private PessoaRepository pessoaRepository;
	@Autowired
	private Messages messages;
	
	public Preferencias adicionar(Preferencias preferencias) {		
		Loja loja = lojaRepository.obterPorId(preferencias.getLoja().getId());
		Pessoa pessoa = pessoaRepository.obterPorIdPessoa(preferencias.getPessoa().getId());
		
		Integer lojaAdicionada = preferenciasRepository.lojaAdicionada(pessoa.getId(), loja.getId());
		
		if (lojaAdicionada > 0) {
			throw new NotAcceptableException(messages.get("MS001"));
		}
		
		preferencias.setLoja(loja);
		preferencias.setPessoa(pessoa);
		
		return preferenciasRepository.save(preferencias);
	}
	
	public String removerLoja(Long idPessoa, Long idLoja) {
		Preferencias preferencia = preferenciasRepository.obterParaRemoverLoja(idPessoa, idLoja);
		
		preferenciasRepository.deleteById(preferencia.getId());
		
		if (preferenciasRepository.obterPorId(preferencia.getId()) == null) {
			return "Loja removida da lista de preferências.";
		}
		
		return "Erro ao remover.";
	}
	
	public List<Loja> obterPorCategoria(Long idCategoria) {
		return preferenciasRepository.obterPorCategoria(idCategoria);
	}
	
	public List<Categoria> obterCategoriasDaListaPorPessoa(Long idPessoa) {
		return preferenciasRepository.obterCategoriasDaListaPorPessoa(idPessoa);
	}
	
	public String limparLista(Long idPessoa) {
		Pessoa pessoa = pessoaRepository.obterPorIdPessoa(idPessoa);
		
		if (pessoa == null)
			throw new NotFoundException(messages.get("MS002"));
		
		List<Long> idsPreferencias = preferenciasRepository.obterIdsPreferencias(idPessoa);
		Integer i = preferenciasRepository.limparLista(idsPreferencias);
		
		if (i > 0) {
			return "A lista de preferência foi limpa.";
		}
		
		return "Erro ao limpar";
	}
}
