package com.apibiblioteca.api_biblioteca.Services;

import com.apibiblioteca.api_biblioteca.Services.exception.InvalidQuantityException;
import com.apibiblioteca.api_biblioteca.Services.exception.ObjectNotFoundException;
import com.apibiblioteca.api_biblioteca.domain.BookInventory;
import com.apibiblioteca.api_biblioteca.domain.Library;
import com.apibiblioteca.api_biblioteca.dto.BookInventoryDTO;
import com.apibiblioteca.api_biblioteca.dto.LibraryDTO;
import com.apibiblioteca.api_biblioteca.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryService {

    @Autowired
    private LibraryRepository repo;

    @Autowired
    private BookService bookService;

    @Autowired
    private BookInventoryService bookInventoryService;

    public List<Library> findAll(){
        return repo.findAll();
    }

    public Library findById(String id){
        Optional<Library> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Library not found"));
    }

    public Library insert(Library obj){
        return repo.insert(obj);
    }

    public void delete(Library obj){
        repo.delete(obj);
    }

    public Library update(Library obj) {
        Library newObj = findById(obj.getId());
        newObj.setName(obj.getName());
        return repo.save(newObj);
    }

    public List<BookInventoryDTO> findBooks(List<String> idList){
        List<BookInventory> list = idList.stream().map(x -> bookInventoryService.findById(x)).toList();
        return list.stream().map(x -> new BookInventoryDTO(x.getId(), bookService.findById(x.getIdBook()).getTitle(), x.getTotalQuantity(), x.getAvailableQuantity())).toList();
    }

    private BookInventory findBooksIventory(Library obj, String id){
        for (String bookId : obj.getIdBooksInventory()) {
            if (bookId.equals(id)){
                return bookInventoryService.findById(bookId);
            }
        }
        throw new ObjectNotFoundException("Book in inventory not found");
    }

    public void availableQuantity(Library obj, String id){
        BookInventory objBook = findBooksIventory(obj, id);
        objBook.setAvailableQuantity(objBook.getAvailableQuantity() - 1);
        if (objBook.getAvailableQuantity() < 0){
            throw new InvalidQuantityException("No available books");
        }
        bookInventoryService.update(objBook);
        repo.save(obj);
    }

    public void returnedQuantity(Library obj, String id){
        BookInventory objBook = findBooksIventory(obj, id);
        objBook.setAvailableQuantity(objBook.getAvailableQuantity() + 1);
        if (objBook.getAvailableQuantity() > objBook.getTotalQuantity() ){
            throw new InvalidQuantityException("No available books");
        }
        bookInventoryService.update(objBook);
        repo.save(obj);
    }

    public BookInventory insertBook(String id, BookInventory objBook){
        Library obj = findById(id);
        bookService.findById(objBook.getIdBook());
        objBook = bookInventoryService.insert(objBook);
        obj.getIdBooksInventory().add(objBook.getId());
        repo.save(obj);
        return objBook;
    }

    public Library fromDto(LibraryDTO objDto){
        return new Library(objDto.getId(), objDto.getName());
    }
}
