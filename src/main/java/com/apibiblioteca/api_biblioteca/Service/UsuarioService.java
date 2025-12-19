package com.apibiblioteca.api_biblioteca.Service;

import com.apibiblioteca.api_biblioteca.domain.Usuario;
import com.apibiblioteca.api_biblioteca.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repo;

    public List<Usuario> findAll(){
        return repo.findAll();
    }

}
