package com.cloudtech.biblioteca.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/")
    public Map<String, String> home() {
        return Map.of("mensagem",
            "Bem-vindo ao sistema de gerenciamento da Biblioteca CloudTech Solutions");
    }

    @GetMapping("/documentacao")
    public Map<String, List<String>> documentacao() {
        return Map.of("endpoints", List.of(
            "/", "/livros", "/livros/cadastrar",
            "/livros/alterar/{id}", "/livros/{id}", "/documentacao"));
    }
}