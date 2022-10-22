package br.com.vaicasar.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.vaicasar.model.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
