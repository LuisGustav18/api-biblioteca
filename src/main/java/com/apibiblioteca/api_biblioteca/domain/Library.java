package com.apibiblioteca.api_biblioteca.domain;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Document
public class Library implements Serializable {

    @Id
    private String id;

    @NotEmpty
    private String name;

    private List<String> idBooksInventory = new ArrayList<>();

    public Library(){

    }

    public Library(String id, String name){
        this.id = id;
        this.name = name;
    }
}
