package com.apibiblioteca.api_biblioteca.domain;

import com.apibiblioteca.api_biblioteca.domain.enums.Condition;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document
public class Book implements Serializable {

    @Id
    private String id;

    @NotEmpty
    private String title;

    @NotEmpty
    private int releaseYear;

    @NotEmpty
    private String isbn;

    private String author;

    @NotEmpty
    private String publisherId;

    private Condition condition;

    public Book(){

    }

    public Book(String id, String title, int releaseYear, String isbn, String author, String publisherId, Condition condition) {
        this.id = id;
        this.title = title;
        this.releaseYear = releaseYear;
        this.isbn = isbn;
        this.author = author;
        this.publisherId = publisherId;
        this.condition = condition;
    }
}
