package br.com.vaicasar.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.vaicasar.model.entity.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

}
