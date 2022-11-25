package br.com.vaicasar.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.vaicasar.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	@Query(value = "SELECT COUNT(u) "
			+ " FROM Usuario u "
			+ " WHERE u.email LIKE '%' || ltrim(:email) || '%' ")
	Integer possuiEmailCadastrado(@Param("email") String email);
}
