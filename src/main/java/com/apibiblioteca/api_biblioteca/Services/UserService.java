package com.apibiblioteca.api_biblioteca.Services;

import com.apibiblioteca.api_biblioteca.Services.exception.ObjectNotFoundException;
import com.apibiblioteca.api_biblioteca.domain.Book;
import com.apibiblioteca.api_biblioteca.domain.User;
import com.apibiblioteca.api_biblioteca.dto.BookDTO;
import com.apibiblioteca.api_biblioteca.repository.BookRepository;
import com.apibiblioteca.api_biblioteca.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private BookService bookService;

    @Autowired
    private PasswordEncoder encoder;

    public List<User> findAll(){
        return repo.findAll();
    }

    public User findById(String id){
        Optional<User> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object user not found"));
    }

    public User insert(User obj){
        obj.setPassword(encryptPassword(obj.getPassword()));
        return repo.insert(obj);
    }

    public void delete(String id){
        repo.delete(findById(id));
    }

    public User update(User Obj){
        User newObj = findById(Obj.getId());
        updateData(newObj, Obj);
        return repo.save(newObj);
    }

    private void updateData(User newObj, User obj){
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
        newObj.setPassword(encryptPassword(obj.getPassword()));
    }

    public Set<BookDTO> findBooks(String id){
        return findById(id).getWishlist().stream().map(x -> new BookDTO(x, bookService.findPublisher(x).getName())).collect(Collectors.toSet());
    }

    public void insertBook(User user, String id){
        Book objBook = bookService.findById(id);
        user.getWishlist().add(objBook);
        repo.save(user);
    }

    private String encryptPassword(String passoword) {
        if (passoword != null) {
            return encoder.encode(passoword);
        }
        throw new IllegalArgumentException("Password cannot be null");
    }
}
