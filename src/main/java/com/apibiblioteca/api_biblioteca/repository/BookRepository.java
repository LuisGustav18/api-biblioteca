package com.apibiblioteca.api_biblioteca.repository;

import com.apibiblioteca.api_biblioteca.domain.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {

    void deleteByPublisherId(String PublisherId);
}
