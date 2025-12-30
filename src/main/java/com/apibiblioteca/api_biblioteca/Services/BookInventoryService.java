package com.apibiblioteca.api_biblioteca.Services;

import com.apibiblioteca.api_biblioteca.Services.exception.ObjectNotFoundException;
import com.apibiblioteca.api_biblioteca.domain.BookInventory;
import com.apibiblioteca.api_biblioteca.repository.BookInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookInventoryService {

    @Autowired
    private BookInventoryRepository repo;

    public BookInventory findById(String id){
        return repo.findById(id).orElseThrow(() -> new ObjectNotFoundException("Book in inventory not found"));
    }

    public BookInventory insert(BookInventory obj){
        return repo.insert(obj);
    }

    public void update(BookInventory obj){
        BookInventory newObj = findById(obj.getId());
        updateData(newObj, obj);
        repo.save(newObj);
    }

    private void updateData(BookInventory newobj, BookInventory obj){
        newobj.setId(obj.getId());
        newobj.setIdBook(obj.getIdBook());
        newobj.setTotalQuantity(obj.getTotalQuantity());
        newobj.setAvailableQuantity(obj.getAvailableQuantity());
    }
}
