package br.com.vaicasar.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.vaicasar.model.entity.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

	@Query(value = "SELECT ta "
			+ " FROM Tarefa ta "
			+ " WHERE ta.id = :idTarefa ")
	public Tarefa obterPorId(@Param("idTarefa") Long idTarefa);
}
