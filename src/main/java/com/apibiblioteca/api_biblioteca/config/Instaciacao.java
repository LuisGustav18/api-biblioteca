package com.apibiblioteca.api_biblioteca.config;

import com.apibiblioteca.api_biblioteca.Service.UsuarioService;
import com.apibiblioteca.api_biblioteca.domain.Usuario;
import com.apibiblioteca.api_biblioteca.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class Instaciacao implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {

        usuarioRepository.deleteAll();

        Usuario user01 = new Usuario(null, "Carlos Andrade", "Carlinhos@gmail.com", "asa4443");
        Usuario user02 = new Usuario(null, "Marquinhos Var", "Var@gmail.com", "Assr443");

        usuarioRepository.saveAll(Arrays.asList(user01, user02));
    }
}
