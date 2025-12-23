package com.apibiblioteca.api_biblioteca.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PublisherDTO implements Serializable {

    private String id;
    private String name;

    public PublisherDTO(String id, String name) {
        this.name = name;
    }
}
