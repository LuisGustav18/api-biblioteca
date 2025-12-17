package com.apibiblioteca.api_biblioteca.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Data
@Document
public class Editora {

    @Id
    private String id;
    private String nome;
    private Set<Livro> livro = new HashSet<>();

    public Editora(String id, String nome, Set<Livro> livro) {
        this.nome = nome;
    }

}
