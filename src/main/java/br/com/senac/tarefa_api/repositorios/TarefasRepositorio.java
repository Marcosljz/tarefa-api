package br.com.senac.tarefa_api.repositorios;

import br.com.senac.tarefa_api.entidades.Tarefas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TarefasRepositorio extends JpaRepository<Tarefas, Long> {

    List<Tarefas> findByNomeContaining(String nome);
    List<Tarefas> findBydataInicioGreaterThan(LocalDate dataInicio);

}
