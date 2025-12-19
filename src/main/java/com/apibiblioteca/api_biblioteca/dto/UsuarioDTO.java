package com.apibiblioteca.api_biblioteca.dto;

import com.apibiblioteca.api_biblioteca.domain.Usuario;
import lombok.Data;

import java.io.Serializable;

@Data
public class UsuarioDTO implements Serializable {

    private String id;
    private String nome;
    private String email;
    private String senha;

    public UsuarioDTO (){

    }

    public UsuarioDTO(Usuario obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.email = obj.getEmail();
        this.senha = obj.getSenha();
    }
}