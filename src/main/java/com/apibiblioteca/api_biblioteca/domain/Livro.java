package com.apibiblioteca.api_biblioteca.domain;

import com.apibiblioteca.api_biblioteca.domain.enums.Condicao;
import com.apibiblioteca.api_biblioteca.domain.enums.Estado;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document(value = "livro")
public class Livro implements Serializable {

    @Id
    private String id;
    private String titulo;
    private int anoDeLancamento;
    private String isbn;
    private Editora editora;
    private Condicao condicao;
    private Estado estado;

    public Livro (){

    }

    public Livro(String id, String titulo, int anoDeLancamento, String isbn, Editora editora, Condicao condicao, Estado estado) {
        this.titulo = titulo;
        this.anoDeLancamento = anoDeLancamento;
        this.isbn = isbn;
        this.editora = editora;
        this.condicao = condicao;
        this.estado = estado;
    }
}
