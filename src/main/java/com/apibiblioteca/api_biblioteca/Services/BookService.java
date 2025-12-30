package com.apibiblioteca.api_biblioteca.Services;

import com.apibiblioteca.api_biblioteca.Services.exception.ObjectNotFoundException;
import com.apibiblioteca.api_biblioteca.domain.Book;
import com.apibiblioteca.api_biblioteca.domain.Publisher;
import com.apibiblioteca.api_biblioteca.dto.BookDTO;
import com.apibiblioteca.api_biblioteca.repository.BookRepository;
import com.apibiblioteca.api_biblioteca.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository repo;

    @Autowired
    private PublisherRepository publisherRepository;

    public List<Book> findAll(){
        return repo.findAll();
    }

    public Book findById(String id){
        Optional<Book> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object book not found"));
    }

    public Book insert(Book obj){
        findPublisher(obj);
        return repo.insert(obj);
    }

    public void delete(Book obj){
        repo.delete(obj);
    }

    public Book update(Book obj){
        Book newObj = findById(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    private void updateData(Book newObj, Book obj){
        newObj.setTitle(obj.getTitle());
        newObj.setReleaseYear(obj.getReleaseYear());
        newObj.setIsbn(obj.getIsbn());
        newObj.setPublisherId(obj.getPublisherId());
        newObj.setCondition(obj.getCondition());
    }

    public Publisher findPublisher(Book obj){
        return publisherRepository.findById(obj.getPublisherId())
                .orElseThrow(() -> new ObjectNotFoundException("Publisher not found"));
    }

    public BookDTO fromDTO(Book obj){

        Publisher publisher = findPublisher(obj);

        BookDTO objDto = new BookDTO(
                obj.getId(),
                obj.getTitle(),
                obj.getReleaseYear(),
                obj.getAuthor(),
                publisher.getName(),
                obj.getCondition().name()
        );
        return objDto;
    }
}
