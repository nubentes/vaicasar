package br.com.vaicasar.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.vaicasar.model.entity.Loja;

public interface LojaRepository extends JpaRepository<Loja, Long> {
	
	@Query(value = "SELECT l "
			+ " FROM Loja l "
			+ " WHERE l.id = :idLoja ")
	public Loja obterPorId(@Param("idLoja") Long idLoja);
	
	@Query(value = "SELECT l"
			+ " FROM Loja l "
			+ " INNER JOIN l.categoria c "
			+ " WHERE c.id = :idCategoria")
	public List<Loja> obterPorCategoria(@Param("idCategoria") Long idCategoria);
}
