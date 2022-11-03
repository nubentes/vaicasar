package br.com.vaicasar.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.vaicasar.model.entity.Categoria;
import br.com.vaicasar.model.repositories.CategoriaRepository;

@Service
@Transactional(readOnly = false)
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public List<Categoria> obterTodos() {
		return categoriaRepository.findAll();
	}
	
	public Categoria salvarCategoria(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	
	public String excluirCategoria(long id) {
		try {
			categoriaRepository.deleteById(id);
			return "Tarefa excluida com sucesso!";
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	
}
