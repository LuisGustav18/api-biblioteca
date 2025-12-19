package com.apibiblioteca.api_biblioteca.repository;

import com.apibiblioteca.api_biblioteca.domain.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {

}
