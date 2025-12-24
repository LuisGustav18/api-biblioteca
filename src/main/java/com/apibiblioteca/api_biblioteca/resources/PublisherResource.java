package com.apibiblioteca.api_biblioteca.resources;

import com.apibiblioteca.api_biblioteca.Services.PublisherService;
import com.apibiblioteca.api_biblioteca.domain.Publisher;
import com.apibiblioteca.api_biblioteca.dto.PublisherDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/publishers")
public class PublisherResource {

    @Autowired
    private PublisherService service;

    @GetMapping
    public ResponseEntity<List<PublisherDTO>> findAll(){
        List<Publisher> list = service.findAll();
        List<PublisherDTO> listDto = list.stream().map(x -> new PublisherDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PublisherDTO> findById(@PathVariable String id){
        Publisher obj = service.findById(id);
        return ResponseEntity.ok().body(new PublisherDTO(obj));
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody Publisher obj){
        service.insert(obj);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @RequestBody Publisher obj){
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
