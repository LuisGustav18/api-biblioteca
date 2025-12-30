package com.apibiblioteca.api_biblioteca.domain;

import com.apibiblioteca.api_biblioteca.domain.enums.Condition;
import com.apibiblioteca.api_biblioteca.domain.enums.BookStatus;
import com.apibiblioteca.api_biblioteca.dto.PublisherDTO;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document
public class Book implements Serializable {

    @Id
    private String id;
    private String title;
    private int releaseYear;
    private String isbn;
    private String author;
    private String publisherId;
    private Condition condition;

    public Book(){

    }

    public Book(String id, String title, int releaseYear, String isbn, String author, String publisherId, Condition condition, BookStatus status) {
        this.id = id;
        this.title = title;
        this.releaseYear = releaseYear;
        this.isbn = isbn;
        this.author = author;
        this.publisherId = publisherId;
        this.condition = condition;
    }
}
