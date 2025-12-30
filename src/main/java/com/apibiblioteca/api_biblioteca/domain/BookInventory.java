package com.apibiblioteca.api_biblioteca.domain;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document
public class BookInventory implements Serializable {

    @Id
    private String id;

    @NotEmpty
    private String idBook;

    @NotEmpty
    private int totalQuantity;

    private int availableQuantity;

    public BookInventory(){

    }

    public BookInventory(String id, String idBook, int totalQuantity) {
        this.id = id;
        this.idBook = idBook;
        this.totalQuantity = totalQuantity;
        this.availableQuantity = totalQuantity;
    }
}
