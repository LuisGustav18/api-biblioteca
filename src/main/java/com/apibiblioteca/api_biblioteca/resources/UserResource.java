package com.apibiblioteca.api_biblioteca.resources;

import com.apibiblioteca.api_biblioteca.Services.UserService;
import com.apibiblioteca.api_biblioteca.domain.Book;
import com.apibiblioteca.api_biblioteca.domain.User;
import com.apibiblioteca.api_biblioteca.dto.BookDTO;
import com.apibiblioteca.api_biblioteca.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        List<User> list = service.findAll();
        List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id){
        User obj = service.findById(id);
        return ResponseEntity.ok().body(new UserDTO(obj));
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody User obj){
        service.insert(obj);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<User> update(@PathVariable String id, @RequestBody User obj){
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}/wishlist")
    public ResponseEntity<Set<Book>> findWishList(@PathVariable String id){
        User obj = service.findById(id);
        return ResponseEntity.ok().body(obj.getWishlist());
    }

    @GetMapping(value = "/{id}/books")
    public ResponseEntity<Set<BookDTO>> findBooks(@PathVariable String id){
        Set<BookDTO> set = service.findBooks(id);
        return ResponseEntity.ok().body(set);
    }

    @PostMapping(value = "/{id}/books/{idBook}")
    public ResponseEntity<Void> insertBook(@PathVariable String id, @PathVariable String idBook){
        User obj = service.findById(id);
        service.insertBook(obj, idBook);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri).build();
    }

}
