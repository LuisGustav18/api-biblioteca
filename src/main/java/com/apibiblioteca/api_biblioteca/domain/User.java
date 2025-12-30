package com.apibiblioteca.api_biblioteca.domain;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Document
public class User implements Serializable {

    @Id
    private String id;

    @NotEmpty
    private String name;

    private String email;

    @NotEmpty
    private String password;

    @DBRef
    private Set<Book> wishlist = new HashSet<>();

    public User(){

    }

    public User(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
