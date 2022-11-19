package br.com.vaicasar.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.vaicasar.model.entity.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	
	@Query(value = "SELECT p "
			+ " FROM Pessoa p "
			+ " INNER JOIN p.usuario u "
			+ " WHERE u.id = :idUsuario ")
	public Pessoa findByIdUsuario(@Param("idUsuario") Long idUsuario);
	
	@Query(value = "SELECT p "
			+ " FROM Pessoa p "
			+ " WHERE p.id = :idPessoa ")
	public Pessoa obterPorIdPessoa(@Param("idPessoa") Long idPessoa);

}
