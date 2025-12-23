package com.apibiblioteca.api_biblioteca.Services;

import com.apibiblioteca.api_biblioteca.Services.exception.ObjectNotFoundException;
import com.apibiblioteca.api_biblioteca.domain.User;
import com.apibiblioteca.api_biblioteca.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

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

    private String encryptPassword(String passoword) {
        if (passoword != null) {
            return encoder.encode(passoword);
        }
        throw new IllegalArgumentException("Password cannot be null");
    }
}
