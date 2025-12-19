package com.apibiblioteca.api_biblioteca.resource;

import com.apibiblioteca.api_biblioteca.Service.UsuarioService;
import com.apibiblioteca.api_biblioteca.domain.Usuario;
import com.apibiblioteca.api_biblioteca.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {

    @Autowired
    private UsuarioService service;

    @GetMapping
    private ResponseEntity<List<UsuarioDTO>> findAll(){
        List<Usuario> list = service.findAll();
        List<UsuarioDTO> listDto = list.stream().map(x -> new UsuarioDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }
}
