package com.apibiblioteca.api_biblioteca.repository;

import com.apibiblioteca.api_biblioteca.domain.BookInventory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookInventoryRepository extends MongoRepository<BookInventory, String> {
}
