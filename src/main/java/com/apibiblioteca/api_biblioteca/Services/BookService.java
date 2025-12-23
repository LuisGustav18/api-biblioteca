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
        Book newobj = findById(obj.getId());
        updateData(newobj, obj);
        return repo.save(newobj);
    }

    private void updateData(Book newobj, Book obj){
        newobj.setId(obj.getId());
        newobj.setTitle(obj.getTitle());
        newobj.setReleaseYear(obj.getReleaseYear());
        newobj.setIsbn(obj.getIsbn());
        newobj.setPublisherId(obj.getPublisherId());
        newobj.setCondition(obj.getCondition());
        newobj.setStatus(obj.getStatus());
    }

    private Publisher findPublisher(Book obj){
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
                obj.getCondition().name(),
                obj.getStatus().name()
        );
        return objDto;
    }
}
