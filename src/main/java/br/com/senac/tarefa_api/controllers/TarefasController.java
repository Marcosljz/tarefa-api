package br.com.senac.tarefa_api.controllers;

import br.com.senac.tarefa_api.dtos.TarefasFiltroDto;
import br.com.senac.tarefa_api.dtos.TarefasRequestDto;
import br.com.senac.tarefa_api.entidades.Tarefas;
import br.com.senac.tarefa_api.services.TarefasService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tarefas")
public class TarefasController {
    private TarefasService tarefasService;

    public TarefasController(TarefasService tarefasService){
        this.tarefasService=tarefasService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Tarefas>> listar(TarefasFiltroDto filtro){
        return ResponseEntity.ok(tarefasService.listar(filtro));
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<Tarefas> listarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(tarefasService.ListaPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(null);
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(null);
        }
    }

    @PostMapping("/criar")
    public ResponseEntity<Tarefas> criar(
            @RequestBody TarefasRequestDto tarefas) {
        try {
            return ResponseEntity
                    .ok(tarefasService.criar(tarefas));
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(null);
        }
    }
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable Long id) {
        try {
            tarefasService.deletar(id);
            return ResponseEntity.ok(null);
        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(null);
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(null);
        }
    }
}

