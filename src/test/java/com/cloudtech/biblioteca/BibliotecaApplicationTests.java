package com.cloudtech.biblioteca;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BibliotecaApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    void paginaInicial() throws Exception {
        mvc.perform(get("/"))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.mensagem").exists());
    }

    @Test
    void listarLivros() throws Exception {
        mvc.perform(get("/livros"))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$[0].titulo").exists());
    }

    @Test
    void buscarPorId() throws Exception {
        mvc.perform(get("/livros/2"))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.titulo").value("Redes de Computadores"));
    }

    @Test
    void buscarIdInexistente() throws Exception {
        mvc.perform(get("/livros/999"))
           .andExpect(status().isNotFound());
    }

    @Test
    void cadastrarLivro() throws Exception {
        String json = """
            {"titulo":"Banco de Dados para Iniciantes","autor":"Maria Souza",
             "editora":"EduTech","ano":2023,"categoria":"Informática","status":"Disponível"}
            """;
        mvc.perform(post("/livros/cadastrar").contentType(MediaType.APPLICATION_JSON).content(json))
           .andExpect(status().isCreated())
           .andExpect(jsonPath("$.id").exists());
    }

    @Test
    void alterarLivro() throws Exception {
        String json = """
            {"titulo":"Banco de Dados Essencial","autor":"Maria Souza",
             "editora":"EduTech","ano":2024,"categoria":"Banco de Dados","status":"Emprestado"}
            """;
        mvc.perform(put("/livros/alterar/1").contentType(MediaType.APPLICATION_JSON).content(json))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.status").value("Emprestado"));
    }

    @Test
    void documentacao() throws Exception {
        mvc.perform(get("/documentacao"))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.endpoints").isArray());
    }
}