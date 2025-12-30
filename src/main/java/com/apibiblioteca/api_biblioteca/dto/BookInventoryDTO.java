package com.apibiblioteca.api_biblioteca.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
public class BookInventoryDTO implements Serializable {

    private String id;
    private String name;
    private int totalQuantity;
    private int availableQuantity;

    public BookInventoryDTO(){

    }

    public BookInventoryDTO(String id, String name, int totalQuantity, int availableQuantity) {
        this.id = id;
        this.name = name;
        this.totalQuantity = totalQuantity;
        this.availableQuantity = availableQuantity;
    }
}
