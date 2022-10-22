package br.com.vaicasar.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.vaicasar.dto.CronogramaDTO;
import br.com.vaicasar.model.entity.Cronograma;
import br.com.vaicasar.model.services.CronogramaService;

@RestController
@RequestMapping(value = "/cronograma", produces = "application/json")
public class CronogramaController {
	
	@Autowired
	private CronogramaService cronogramaService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/criar")
	public Cronograma criar(@RequestBody(required = true) CronogramaDTO cronogramaDto) {
		return cronogramaService.criar(cronogramaDto);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "obter")
	public Cronograma obterCronograma(@RequestParam(name = "id", required = true) Long id) {
		return cronogramaService.obterPorId(id);
	}

}
