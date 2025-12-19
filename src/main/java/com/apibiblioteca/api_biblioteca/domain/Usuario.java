package com.apibiblioteca.api_biblioteca.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Document
public class Usuario implements Serializable {

    @Id
    private String id;
    private String nome;
    private String email;
    private String senha;
    private Set<Livro> listaDeDesejo = new HashSet<>();

    public Usuario (){

    }

    public Usuario(String id, String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }
}
