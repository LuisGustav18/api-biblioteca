package com.apibiblioteca.api_biblioteca.Services;

import com.apibiblioteca.api_biblioteca.Services.exception.ObjectNotFoundException;
import com.apibiblioteca.api_biblioteca.domain.Publisher;
import com.apibiblioteca.api_biblioteca.repository.BookRepository;
import com.apibiblioteca.api_biblioteca.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {

    @Autowired
    private PublisherRepository repo;

    @Autowired
    private BookRepository bookRepository;

    public List<Publisher> findAll(){
        return repo.findAll();
    }

    public Publisher findById(String id){
        return repo.findById(id).orElseThrow(() -> new ObjectNotFoundException("Publisher not found"));
    }

    public Publisher insert(Publisher obj){
        return repo.insert(obj);
    }

    public void delete(String id){
        Publisher findCorrect = findById(id);
        bookRepository.findAll().stream().filter(x -> x.getPublisherId().equals(findCorrect.getId())).forEach(x -> bookRepository.delete(x));
        repo.delete(findCorrect);
    }

    public Publisher update(Publisher obj){
        Publisher newObj = findById(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    public void updateData(Publisher newObj, Publisher obj){
        newObj.setName(obj.getName());
    }
}
