package com.apibiblioteca.api_biblioteca.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Document
public class Publisher implements Serializable {

    @Id
    private String id;
    private String name;

    public Publisher(String id, String name) {
        this.name = name;
    }

}
