package br.com.vaicasar.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.vaicasar.dto.CronogramaDTO;
import br.com.vaicasar.model.entity.Cronograma;
import br.com.vaicasar.model.entity.Pessoa;
import br.com.vaicasar.model.repositories.CronogramaRepository;

@Service
@Transactional(readOnly = false)
public class CronogramaService {

	@Autowired
	private PessoaService pessoaService;
	@Autowired
	private CronogramaRepository cronogramaRepository;
	
	public Cronograma criar(CronogramaDTO cronogramaDto) {
		Pessoa pessoa = pessoaService.findByIdUsuario(cronogramaDto.getUsuario().getId());
		
		Cronograma cronograma = new Cronograma();
		cronograma.setPessoa(pessoa);
		cronograma.setDataPrevista(cronogramaDto.getDataPrevista());
		
		return cronogramaRepository.save(cronograma);
	}
}
