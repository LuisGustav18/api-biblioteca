package com.apibiblioteca.api_biblioteca.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document
public class Publisher implements Serializable {

    @Id
    private String id;
    private String name;

    public Publisher(String id, String name) {
        this.id = id;
        this.name = name;
    }

}
