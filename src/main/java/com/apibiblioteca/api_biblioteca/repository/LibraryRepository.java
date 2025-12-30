package com.apibiblioteca.api_biblioteca.repository;

import com.apibiblioteca.api_biblioteca.domain.Library;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LibraryRepository extends MongoRepository<Library, String> {
}
