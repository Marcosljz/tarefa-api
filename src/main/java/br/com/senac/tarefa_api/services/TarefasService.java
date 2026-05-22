package br.com.senac.tarefa_api.services;

import br.com.senac.tarefa_api.dtos.TarefasFiltroDto;
import br.com.senac.tarefa_api.dtos.TarefasRequestDto;
import br.com.senac.tarefa_api.entidades.Tarefas;
import br.com.senac.tarefa_api.repositorios.TarefasRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefasService {
    private TarefasRepositorio tarefasRepositorio;

    public TarefasService(TarefasRepositorio tarefasRepositorio) {
        this.tarefasRepositorio = tarefasRepositorio;
    }

    public List<Tarefas> listar(TarefasFiltroDto filtro) {
        if (filtro.getNome() != null) {
            return tarefasRepositorio.findByNomeContaining(filtro.getNome());
        }
        if (filtro.getDataInicio() != null) {
            return tarefasRepositorio.findBydataInicioGreaterThan(filtro.getDataInicio());
        }
        return tarefasRepositorio.findAll();
    }

    public Tarefas criar(TarefasRequestDto tarefas) {
        Tarefas tarefasPersist =
                this.tarefasResquestDtoParaTarefas(tarefas);

        return tarefasRepositorio.save(tarefasPersist);
    }

    public Tarefas atualizar(
            Long id,
            TarefasRequestDto tarefas) {

        if (tarefasRepositorio.existsById(id)) {
            Tarefas tarefasPersist =
                    this.tarefasResquestDtoParaTarefas(tarefas);
            tarefasPersist.setId(id);
        }

        throw new RuntimeException("Tarefa Não foi Encontrada");
    }

    public void deletar(Long id) {
        if (tarefasRepositorio.existsById(id)) {
            tarefasRepositorio.deleteById(id);
        }
        throw new RuntimeException("Tarefa não Encontrada");
    }

    public Tarefas ListaPorId(Long id) {
        Optional<Tarefas> retorno = tarefasRepositorio.findById(id);
        if (retorno.isPresent()) {
            return retorno.get();
        }
        throw new RuntimeException("Tarefa Não encontrada");
    }

    private Tarefas tarefasResquestDtoParaTarefas(
            TarefasRequestDto entrada) {

        Tarefas saida = new Tarefas();
        saida.setNome(entrada.getNome());
        saida.setDescricao(entrada.getDescricao());
        saida.setDataInicio(entrada.getDataInicio());
        saida.setDataFinal(entrada.getDataFinal());
        saida.setAutor(entrada.getAutor());

        return saida;
    }

}
