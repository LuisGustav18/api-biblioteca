package com.apibiblioteca.api_biblioteca.resources;

import com.apibiblioteca.api_biblioteca.Services.LibraryService;
import com.apibiblioteca.api_biblioteca.domain.BookInventory;
import com.apibiblioteca.api_biblioteca.domain.Library;
import com.apibiblioteca.api_biblioteca.dto.BookInventoryDTO;
import com.apibiblioteca.api_biblioteca.dto.LibraryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "libraries")
public class LibraryResource {

    @Autowired
    private LibraryService service;

    @GetMapping
    public ResponseEntity<List<LibraryDTO>> findAll(){
        List<Library> list = service.findAll();
        List<LibraryDTO> listDto = list.stream().map(x -> new LibraryDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<LibraryDTO> findById(@PathVariable String id){
        Library obj = service.findById(id);
        return ResponseEntity.ok().body(new LibraryDTO(obj));
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody LibraryDTO objDto){
        Library obj = service.fromDto(objDto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @RequestBody LibraryDTO objDto){
        objDto.setId(id);
        service.update(service.fromDto(objDto));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        service.delete(service.findById(id));
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}/booksinventory")
    public ResponseEntity<List<BookInventoryDTO>> findBooks(@PathVariable String id){
        List<String> listId = service.findById(id).getIdBooksInventory();
        List<BookInventoryDTO> list = service.findBooks(listId);
        return ResponseEntity.ok().body(list);
    }

    @PostMapping(value = "/{id}/booksinventory")
    public ResponseEntity<Void> insertBooksInventory(@PathVariable String id, @RequestBody BookInventory obj){
        service.insertBook(id, obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}/booksinventory/{idBookInventory}/borrow")
    public ResponseEntity<Void> borrowBook(@PathVariable String id, @PathVariable String idBookInventory){
        service.availableQuantity(service.findById(id), idBookInventory);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}/booksinventory/{idBookInventory}/return")
    public ResponseEntity<Void> returnBook(@PathVariable String id, @PathVariable String idBookInventory){
        service.returnedQuantity(service.findById(id), idBookInventory);
        return ResponseEntity.noContent().build();
    }
}
