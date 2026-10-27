package com.cloudtech.biblioteca.Controller;

import com.cloudtech.biblioteca.model.Livro;
import com.cloudtech.biblioteca.service.LivroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroService service;

    public LivroController(LivroService service) {
        this.service = service;
    }

    @GetMapping
    public List<Livro> listar() {
        return service.listar();
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Livro> cadastrar(@RequestBody Livro livro) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrar(livro));
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<Livro> alterar(@PathVariable Long id, @RequestBody Livro livro) {
        return service.alterar(id, livro)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscar(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}