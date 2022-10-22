package br.com.vaicasar.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.vaicasar.model.entity.Cronograma;

public interface CronogramaRepository extends JpaRepository<Cronograma, Long> {

	@Query(value = "SELECT co "
			+ " FROM Cronograma co "
			+ " INNER JOIN FETCH co.tarefas ta "
			+ " WHERE co.id = :id")
	public Cronograma obterPorId(@Param("id") Long id);
	
	@Query(value = "SELECT co FROM Cronograma co WHERE co.id = :id")
	public Cronograma findByid(@Param("id") Long id);
}
