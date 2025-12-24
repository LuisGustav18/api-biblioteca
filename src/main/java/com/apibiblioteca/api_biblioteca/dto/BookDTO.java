package com.apibiblioteca.api_biblioteca.dto;

import com.apibiblioteca.api_biblioteca.domain.Book;
import lombok.Data;

import java.io.Serializable;

@Data
public class BookDTO implements Serializable {

    private String id;
    private String title;
    private int releaseYear;
    private String author;
    private String publisherName;
    private String condition;
    private String status;

    public BookDTO(){

    }

    public BookDTO(String id, String title, int releaseYear, String author, String publisherName, String condition, String status) {
        this.id = id;
        this.title = title;
        this.releaseYear = releaseYear;
        this.author = author;
        this.publisherName = publisherName;
        this.condition = condition;
        this.status = status;
    }

    public BookDTO(Book obj, String namePublisher){
        this.id = obj.getId();
        this.title = obj.getTitle();
        this.releaseYear = obj.getReleaseYear();
        this.author = obj.getAuthor();
        this.publisherName = namePublisher;
        this.condition = obj.getCondition().name();
        this.status = obj.getStatus().name();
    }
}
