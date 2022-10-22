package br.com.vaicasar.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.vaicasar.model.entity.Loja;

public interface LojaRepository extends JpaRepository<Loja, Long> {

	@Query(value = "SELECT loja "
			+ " FROM Loja loja "
			+ " INNER JOIN FETCH loja.endereco ende "
			+ " INNER JOIN FETCH loja.categoria cate "
			+ " INNER JOIN FETCH loja.fotosLoja fotos ")
	public List<Loja> obterTodos();
}
