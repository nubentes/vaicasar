package br.com.vaicasar.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.vaicasar.model.entity.Cronograma;

public interface CronogramaRepository extends JpaRepository<Cronograma, Long> {

	@Query(value = "SELECT co "
			+ " FROM Cronograma co "
			+ " LEFT JOIN FETCH co.tarefas ta "
			+ " WHERE co.id = :id")
	public Cronograma obterPorId(@Param("id") Long id);
	
	@Query(value = "SELECT COUNT(co) "
			+ " FROM Cronograma co "
			+ " INNER JOIN co.pessoa pe "
			+ " WHERE pe.id = :idPessoa ")
	public Integer possuiCronograma(@Param("idPessoa") Long idPessoa);
	
	@Query(value = "SELECT co "
			+ " FROM Cronograma co "
			+ " INNER JOIN FETCH co.tarefas ta "
			+ " INNER JOIN co.pessoa pe "
			+ " INNER JOIN pe.usuario u "
			+ " WHERE u.id = :idUsuario")
	public Cronograma obterPorIdUsuario(@Param("idUsuario") Long idUsuario);
	
	@Query(value = "SELECT co "
			+ " FROM Cronograma co "
			+ " INNER JOIN FETCH co.tarefas ta "
			+ " INNER JOIN co.pessoa pe "
			+ " WHERE pe.id = :idPessoa")
	public Cronograma obterPorIdPessoa(@Param("idPessoa") Long idPessoa);
}
