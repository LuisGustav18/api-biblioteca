package com.apibiblioteca.api_biblioteca.repository;

import com.apibiblioteca.api_biblioteca.domain.Publisher;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends MongoRepository<Publisher, String> {

}
