package com.example.ReactSpringProject.crudBackend.controller;

import com.example.ReactSpringProject.crudBackend.entity.User;
import com.example.ReactSpringProject.crudBackend.exception.UserNotFoundException;
import com.example.ReactSpringProject.crudBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @PostMapping("user")
    public User saveUser (@RequestBody User user){
        return userRepository.save(user);
    }
    @GetMapping("users")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    @GetMapping("user/{id}")
    public User getUserById(@PathVariable Long id){
        return userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(id));
    }
    @PutMapping("user/{id}")
    public User updateUser(@RequestBody User user, @PathVariable Long id){
        return  userRepository.findById(id).map(update-> {
            update.setUsername(user.getUsername());
            update.setPassword(user.getPassword());
            update.setEmail(user.getEmail());
            return userRepository.save(update);
        }).orElseThrow(()-> new UserNotFoundException(id));
    }
    @DeleteMapping("user/{id}")
    public String deleteUser(@PathVariable Long id){
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return "User with id" +id+ "has been deleted successfully!!";
    }
}
