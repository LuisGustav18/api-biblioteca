package com.apibiblioteca.api_biblioteca.dto;

import com.apibiblioteca.api_biblioteca.domain.Library;
import lombok.Data;

import java.io.Serializable;

@Data
public class LibraryDTO implements Serializable {

    private String id;
    private String name;

    public LibraryDTO(){

    }

    public LibraryDTO(Library obj){
        this.id = obj.getId();
        this.name = obj.getName();
    }
}
