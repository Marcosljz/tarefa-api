package br.com.senac.tarefa_api.dtos;

import java.time.LocalDate;

public class TarefasFiltroDto {

    private String nome;
    private LocalDate dataInicio;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }
}
