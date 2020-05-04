package com.example.demo.service;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;


    @Autowired
    public void setRepository(UserRepository repository) {
        this.repository = repository;
    }


    @Transactional
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public List<User> getAll() {
        List<User> userEntities = repository.findAll();
        return userEntities;
    }

    @Transactional
    public User getById(Long id) {
        Optional<User> user = repository.findById(id);
        if (user.isPresent()){
            return user.get();
        }
        else
            throw new UserNotFoundException(id);

    }

    @Transactional
    public User createUser(User newUser) {
        newUser = repository.save(newUser);

        return newUser;
    }

}
