package com.apibiblioteca.api_biblioteca.dto;

import com.apibiblioteca.api_biblioteca.domain.Publisher;
import lombok.Data;

import java.io.Serializable;

@Data
public class PublisherDTO implements Serializable {

    private String id;
    private String name;

    public PublisherDTO(){

    }

    public PublisherDTO(Publisher obj) {
        this.id = obj.getId();
        this.name = obj.getName();
    }
}
