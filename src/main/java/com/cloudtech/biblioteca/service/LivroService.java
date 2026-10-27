package com.cloudtech.biblioteca.service;

import com.cloudtech.biblioteca.model.Livro;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class LivroService {

    private final List<Livro> livros = new ArrayList<>();
    private final AtomicLong contador = new AtomicLong(1);

    public LivroService() {
        cadastrar(novo("Introdução à Programação", "João Silva", "Tech Books", 2022, "Tecnologia", "Disponível"));
        cadastrar(novo("Redes de Computadores", "Carlos Mendes", "Net Books", 2021, "Redes", "Disponível"));
    }

    private Livro novo(String t, String a, String e, int ano, String c, String s) {
        Livro l = new Livro();
        l.setTitulo(t); l.setAutor(a); l.setEditora(e);
        l.setAno(ano); l.setCategoria(c); l.setStatus(s);
        return l;
    }

    public List<Livro> listar() {
        return livros;
    }

    public Optional<Livro> buscarPorId(Long id) {
        return livros.stream().filter(l -> l.getId().equals(id)).findFirst();
    }

    public Livro cadastrar(Livro livro) {
        livro.setId(contador.getAndIncrement());
        livros.add(livro);
        return livro;
    }

    public Optional<Livro> alterar(Long id, Livro dados) {
        return buscarPorId(id).map(l -> {
            l.setTitulo(dados.getTitulo());
            l.setAutor(dados.getAutor());
            l.setEditora(dados.getEditora());
            l.setAno(dados.getAno());
            l.setCategoria(dados.getCategoria());
            l.setStatus(dados.getStatus());
            return l;
        });
    }
}