package com.apibiblioteca.api_biblioteca.dto;

import com.apibiblioteca.api_biblioteca.domain.User;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserDTO implements Serializable {

    private String id;
    private String name;
    private String email;

    public UserDTO(){

    }

    public UserDTO(User obj) {
        this.id = obj.getId();
        this.name = obj.getName();
        this.email = obj.getEmail();
    }
}