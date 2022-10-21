package br.com.vaicasar.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.vaicasar.model.entity.Cronograma;

public interface CronogramaRepository extends JpaRepository<Cronograma, Long> {

}
